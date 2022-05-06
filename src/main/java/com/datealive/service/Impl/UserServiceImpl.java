package com.datealive.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.datealive.common.PageResult;
import com.datealive.common.ResultCode;
import com.datealive.common.StaticFinalCode;
import com.datealive.entity.dto.BigUserDetail;
import com.datealive.entity.dto.ResumeDto;
import com.datealive.entity.pojo.Resume;
import com.datealive.entity.pojo.User;
import com.datealive.entity.vo.User.UpdateUserDetail;
import com.datealive.entity.vo.User.UserGradeObj;
import com.datealive.entity.vo.User.UserListObj;
import com.datealive.mapper.ResumeMapper;
import com.datealive.mapper.UserListObjMapper;
import com.datealive.mapper.UserMapper;
import com.datealive.service.RedisService;
import com.datealive.service.ResumeService;
import com.datealive.service.UserListObjService;
import com.datealive.service.UserService;
import com.datealive.utils.JacksonUtils;
import com.datealive.utils.RedisUtil;
import com.github.pagehelper.PageHelper;
import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@Data
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private UserListObjMapper userListObjMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserListObjService userListObjService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<UserGradeObj> getUserGradeTop10() {

        //查询缓存中是否有top10的key 如果有 则返回缓存中的
        if (redisUtil.hasKey(StaticFinalCode.UserTop10ByRateCountKey)) {
            Object obj = redisUtil.get(StaticFinalCode.UserTop10ByRateCountKey);
            String text = JSONObject.toJSONString(obj);
            List<UserGradeObj> userGradeObjs = JSON.parseArray(text, UserGradeObj.class);
            return userGradeObjs;
        }else{
            //如果缓存中没有 在数据库中找 然后缓    存到redis中 有效期是一天
            List<UserGradeObj> top10 = userListObjService.getUserGradeTop10();
            redisService.setTopDataKey(StaticFinalCode.UserTop10ByRateCountKey,
                                       top10,
                                       StaticFinalCode.topKey_TIMEOUT);
            return top10;
        }
    }

    @Override
    public BigUserDetail getBigUserByID(Long userId) {
        //现在redis中查 如果有
        if (redisUtil.hHasKey(StaticFinalCode.UserAndResumeKey, String.valueOf(userId))) {
            Object obj = redisUtil.hget(StaticFinalCode.UserAndResumeKey, String.valueOf(userId));
            BigUserDetail bigUserDetail = JacksonUtils.convertValue(obj, BigUserDetail.class);
            return bigUserDetail;
        }else{
            BigUserDetail bigUserDetail = new BigUserDetail();
            User user = userMapper.getUserDetailByID(userId);
            if (user!=null) {
                //给用户大对象插入用户信息
                bigUserDetail.setUser(user);
                //给大用户插入简历信息 这里简历信息还包括了竞赛经验集合
                ResumeDto resumedto = resumeService.getResumeDtoByResumeId(user.getResumeId());
                bigUserDetail.setResumeDto(resumedto);
                //插入后对redis进行更新
                redisService.hsetUserAndResumeKey(String.valueOf(user.getId()),bigUserDetail);
            }
            return bigUserDetail;
        }

    }

    /**
     * 获得用户信息
     * @param ID
     * @return
     */
    @Override
    public User getUserByID(@Param("ID") Long ID) {
        User userDetailByID = userMapper.getUserDetailByID(ID);
        return userDetailByID;
    }

    /**
     * 更新用户的信息（非简历部分）
     * @param user
     * @return
     */
    @Override
    public boolean updateUserDetail(@RequestBody UpdateUserDetail user) {
        Boolean aBoolean = userMapper.updateUserDetailMessageByID(user);
        if(aBoolean){
            //如果更新成功了，删除用户缓存 这里后期可以考虑细粒度
            redisService.hdelKeyAndItem(StaticFinalCode.UserAndResumeKey, String.valueOf(user.getId()));
        }
        return aBoolean;
    }

    /**
     * 更新用户头像
     * @param avatar
     * @param ID
     * @return
     */
    @Override
    public boolean updateUserAvatar(@Param("avatar") String avatar,@Param("ID")Long ID) {
        Boolean aBoolean=userMapper.updateUserAvatar(avatar,ID);
        if(aBoolean){
            //如果更新成功了，删除用户缓存 这里后期可以考虑细粒度
            redisService.hdelKeyAndItem(StaticFinalCode.UserAndResumeKey, String.valueOf(ID));
        }
        return aBoolean;
    }

    /**
     * 获取用户列表
     * @param nickname
     * @return
     */
    @Override
    public List<UserListObj> getUserListByNickname(@Param("nickname") String nickname) {
        return userMapper.getUserListByNickname(nickname);
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertUser(@RequestBody User user) {
        //先进行简历的注册 然后绑定
        Resume resume=new Resume();
        resume.setName(user.getNickName());
        resume.setGender(user.getGender());
        Long resumeID = resumeService.createResume(resume);
        user.setResumeId(resumeID);
        user.setIsindentify(1);
        return userMapper.insertUser(user);
    }

    @Override
    public User findUserByOpenId(String openId) {
        User userByOpenId = userMapper.findUserByOpenId(openId);
        if(userByOpenId!=null){
            //如果查询到这个人的话，就缓存他的userID和OpenID到redis中
            redisService.hsetUidAndOpenidKey(String.valueOf(userByOpenId.getId()),userByOpenId.getOpenID());
            return userByOpenId;
        }
        return null;
    }

    @Override
    public String getOpenIdByUserId(Long userID) {
        //先在redis中找 如果有
        String openIdByUserId="";
        if (redisUtil.hHasKey(StaticFinalCode.UidAndOpenidKey, String.valueOf(userID))) {
            openIdByUserId = (String) redisUtil.hget(StaticFinalCode.UidAndOpenidKey, String.valueOf(userID));

        }else{
            //如果redis找不到 后面需要加个"" 要不然会有空指针异常
            openIdByUserId = userMapper.findOpenIdByUserId(userID);
            //如果在mysql找不到openid，说明没有这个用户 直接返回false
            if (openIdByUserId!=null) {
                redisService.hsetUidAndOpenidKey(String.valueOf(userID),openIdByUserId);
            }
        }

        return openIdByUserId;
    }

    @Override
    public boolean findOpenIdByUserId(Long userID,String openId) {
        //先在redis中找 如果有
        String openIdByUserId="";
        if (redisUtil.hHasKey(StaticFinalCode.UidAndOpenidKey, String.valueOf(userID))) {
            openIdByUserId = (String) redisUtil.hget(StaticFinalCode.UidAndOpenidKey, String.valueOf(userID));

        }else{
            //如果redis找不到 后面需要加个"" 要不然会有空指针异常
            openIdByUserId = userMapper.findOpenIdByUserId(userID);
            //如果在mysql找不到openid，说明没有这个用户 直接返回false
            if (openIdByUserId==null) {
                return false;
            }else{
                redisService.hsetUidAndOpenidKey(String.valueOf(userID),openIdByUserId);
            }
        }
        //如果相同
        if (openIdByUserId.equals(openId)) {
            return true;
        }
        return false;
    }

    @Override
    public User getUserDetailByID(@Param("ID") Long userID) {
        return userMapper.getUserDetailByID(userID);
    }

    @Override
    public boolean updateEmail(String email, Long userID) {
        Boolean aBoolean=userMapper.updateEmail(email, userID);
        if(aBoolean){
            //如果更新成功了，删除用户缓存 这里后期可以考虑细粒度
            redisService.hdelKeyAndItem(StaticFinalCode.UserAndResumeKey, String.valueOf(userID));
        }
        return aBoolean;
    }

    @Override
    public boolean updatePhone(String phone, Long userID) {
        Boolean aBoolean=userMapper.updatePhone(phone,userID);
        if(aBoolean){
            //如果更新成功了，删除用户缓存 这里后期可以考虑细粒度
            redisService.hdelKeyAndItem(StaticFinalCode.UserAndResumeKey, String.valueOf(userID));
        }
        return aBoolean;
    }

    @Override
    public boolean updateUsername(String username, Long userID) {
        Boolean aBoolean= userMapper.updateUsername(username,userID);
        if(aBoolean){
            //如果更新成功了，删除用户缓存 这里后期可以考虑细粒度
            redisService.hdelKeyAndItem(StaticFinalCode.UserAndResumeKey, String.valueOf(userID));
        }
        return aBoolean;
    }

    @Override
    public boolean connect(Long userID, Long resumeID) {
        return userMapper.connectUserToResumeByID(userID,resumeID);
    }

    @Override
    public UserGradeObj getUserAndRateCntByUserID(Long userID) {
        UserGradeObj userAndRateCntByTeam = userListObjMapper.getUserAndRateCntByTeam(userID);
        UserGradeObj userAndRateCntByTeammate = userListObjMapper.getUserAndRateCntByTeammate(userID);
        userAndRateCntByTeam.setRateCnt(userAndRateCntByTeammate.getRateCnt()+userAndRateCntByTeam.getRateCnt());
        return userAndRateCntByTeam;
    }

}

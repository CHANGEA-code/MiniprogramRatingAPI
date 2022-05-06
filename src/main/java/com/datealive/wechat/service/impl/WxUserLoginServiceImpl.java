package com.datealive.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.datealive.common.StaticFinalCode;
import com.datealive.entity.pojo.User;
import com.datealive.service.UserService;
import com.datealive.utils.RedisUtil;
import com.datealive.utils.StringUtils;
import com.datealive.wechat.entity.WxUserDto;
import com.datealive.wechat.service.WxMiniApi;
import com.datealive.wechat.service.WxUserLoginService;
import com.datealive.wechat.utils.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @ClassName: WxUserLoginServiceImpl
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/7  15:59
 */
@Service
@Component
public class WxUserLoginServiceImpl implements WxUserLoginService {



    public static String appid;


    public static String secret;

    @Value("${WeChat.appid}")
    public void setAppid(String appid) {
        WxUserLoginServiceImpl.appid = appid;
    }

    @Value("${WeChat.secret}")
    public void setSecret(String secret) {
        WxUserLoginServiceImpl.secret = secret;
    }

    @Autowired
    private WxMiniApi wxMiniApi;


    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public WxUserDto UserLoginAndRegister(WxUserDto wxUserDto, HttpServletResponse response) {
        String jscode = wxUserDto.getCode();
        if(!StringUtils.isEmpty(jscode)){
            JSONObject jsonObject = wxMiniApi.authCode2Session(appid, secret, jscode);
            if(jsonObject==null){
                throw new RuntimeException("小程序用户授权失败");
            }
            String openID = jsonObject.getString(StaticFinalCode.OPEN_ID);
            String session_key = jsonObject.getString(StaticFinalCode.SESSION_KEY);
            if (StringUtils.isEmpty(openID)) {
                return null;
            }
            wxUserDto.setOpenId(openID);
            response.setHeader("Authorization",openID);
            //查询用户表中是否存在改用户 如果存在 则返回这个用户 信息 如果不存在 则进行新增用户并返回
            //数据

            User userByOpenId = userService.findUserByOpenId(openID);
            //如果不存在 则新增用户
            if (userByOpenId==null) {
                String userInfo = WeChatUtil.decryptData(wxUserDto.getEncryptedData(), session_key, wxUserDto.getIv());

                User user = JSONObject.parseObject(userInfo, User.class);
                //这里执行新增用户逻辑
                //首先 这里的username 先与微信名称一致
                user.setUsername(user.getNickName());
                //password 的话 这里是不需要的 后期可能去掉 所以一直保存null就行
                //********************************
                //email需要登录后在填写

                //创建时间 在这里新增的时候生成
                user.setCreateTime(new Date());

                //电话号码 需要授权获取后插入 插入电话号码 代表已经认证成功
                //此时 phone和isindentify 都要做修改
                //这里先插入 0 表示未认证
                user.setIsindentify(0);

                //其他的类似什么省份 城市 如果有 就给 如果没有 就不用给 前面 使用fastjson映射了 有则有
                //openID给下吧

                user.setOpenID(openID);

                Boolean insertUser = userService.insertUser(user);
                //如果插入成功 则返回给前端
                if(insertUser){
                    response.setHeader("UserId", String.valueOf(user.getId()));
                    wxUserDto.setUser(user);
                    return wxUserDto;
                }
                return null;


            }
            else{
                //如果存在 则将获取到的信息返回
                response.setHeader("UserId", String.valueOf(userByOpenId.getId()));
                wxUserDto.setUser(userByOpenId);
            }
            return wxUserDto;
        }else{
            return null;
        }


    }




}

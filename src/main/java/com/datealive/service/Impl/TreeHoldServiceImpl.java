package com.datealive.service.Impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import com.datealive.common.PageResult;
import com.datealive.common.ResultCode;
import com.datealive.common.StaticFinalCode;
import com.datealive.entity.dto.TreeHoldDto;
import com.datealive.entity.pojo.TreeHold;
import com.datealive.entity.pojo.User;
import com.datealive.entity.vo.User.UserListObj;
import com.datealive.mapper.TreeHoldMapper;
import com.datealive.mapper.UserMapper;
import com.datealive.service.TreeHoldService;
import com.datealive.service.UserService;
import com.datealive.utils.JacksonUtils;
import com.datealive.utils.RedisUtil;
import com.datealive.wechat.service.WxSubscribeMessage;
import com.datealive.wechat.service.impl.WxSubscribeMessageImpl;
import com.github.pagehelper.PageHelper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Data
public class TreeHoldServiceImpl implements TreeHoldService {
    @Autowired
    private TreeHoldMapper treeHoldMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WxSubscribeMessageImpl wxSubscribeMessage;

    @Override
    public boolean createTreeHold(TreeHold treeHold) {
        treeHold.setCreateTime(new Date());
        String accessToken = wxSubscribeMessage.getAccessToken();

        if (isMsgSecurity(treeHold.getContent(),accessToken)) {
            return treeHoldMapper.createTreeHold(treeHold);
        }else{
            return false;
        }

    }

    private boolean isMsgSecurity(String content,String accessToken){
        JSONObject body = new JSONObject();
        body.set("content",content);
        String post = HttpUtil.post("https://api.weixin.qq.com/wxa/msg_sec_check?access_token=" + accessToken, body.toString());

        JSONObject jsonObject = JSONUtil.parseObj(post);
        String errcode = jsonObject.getStr("errcode");
        String errmsg = jsonObject.getStr("errmsg");

        if("0".equals(errcode)){
            return true;
        }
        return false;


    }

    @Override
    public boolean deleteTreeHold(Long id) {
        return treeHoldMapper.deleteTreeHold(id);
    }

    @Override
    public boolean updateTreeHoldContent(Long id, String content) {
        String accessToken = wxSubscribeMessage.getAccessToken();
        if (isMsgSecurity(content,accessToken)) {
            return treeHoldMapper.updateTreeHoldContext(id,content);
        }else{
            return false;
        }

    }

    @Override
    public TreeHoldDto getTreeHoldByID(Long id) {
        TreeHold treeHoldByID = treeHoldMapper.getTreeHoldByID(id);
        UserListObj user = userMapper.getUserListObjByID(treeHoldByID.getUserID());
        TreeHoldDto treeHoldDto = new TreeHoldDto();
        treeHoldDto.setId(treeHoldByID.getId());
        treeHoldDto.setUserID(treeHoldByID.getUserID());
        treeHoldDto.setContent(treeHoldByID.getContent());
        treeHoldDto.setAvatar(user.getAvatar());
        treeHoldDto.setUserName(user.getNickname());
        treeHoldDto.setCreateTime(treeHoldByID.getCreateTime());

        return treeHoldDto;
    }

    @Override
    public List<TreeHoldDto> getTreeHoldListByUserID(Long userID) {
        List<TreeHold> treeHoldListByUserID = treeHoldMapper.getTreeHoldListByUserID(userID);
        List<TreeHoldDto> list=new ArrayList<>();
        for (TreeHold treeHold : treeHoldListByUserID) {
            TreeHoldDto treeHoldDto = new TreeHoldDto();
            UserListObj user = userMapper.getUserListObjByID(treeHold.getUserID());
            treeHoldDto.setId(treeHold.getId());
            treeHoldDto.setUserID(treeHold.getUserID());
            treeHoldDto.setContent(treeHold.getContent());
            treeHoldDto.setAvatar(user.getAvatar());
            treeHoldDto.setUserName(user.getNickname());
            treeHoldDto.setCreateTime(treeHold.getCreateTime());
            list.add(treeHoldDto);
        }
        return list;
    }

    @Override
    public PageResult<TreeHoldDto> getTreeHoldList(Integer pageNum) {
        Long allTreeHoldCnt = treeHoldMapper.getAllTreeHoldCnt();
        int totalPage=(int)Math.ceil(allTreeHoldCnt*1.0/StaticFinalCode.pageSize_5);
        PageHelper.startPage(pageNum,StaticFinalCode.pageSize_5);
        List<TreeHold> treeHoldList=null;
        treeHoldList=treeHoldMapper.getTreeHoldList();
        List<TreeHoldDto> list=new ArrayList<>();
        for (TreeHold treeHold : treeHoldList) {
            TreeHoldDto treeHoldDto = new TreeHoldDto();
            UserListObj user = userMapper.getUserListObjByID(treeHold.getUserID());
            treeHoldDto.setId(treeHold.getId());
            treeHoldDto.setUserID(treeHold.getUserID());
            treeHoldDto.setContent(treeHold.getContent());
            treeHoldDto.setAvatar(user.getAvatar());
            treeHoldDto.setUserName(user.getNickname());
            treeHoldDto.setCreateTime(treeHold.getCreateTime());
            list.add(treeHoldDto);
        }
        if(treeHoldList.isEmpty()){
            return new PageResult<TreeHoldDto>(ResultCode.Not_Found,totalPage,pageNum,null);
        }else {
            return new PageResult<TreeHoldDto>(ResultCode.Success,totalPage,pageNum,list);
        }

    }
}

package com.datealive.service.Impl.team.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.datealive.common.StaticFinalCode;
import com.datealive.entity.dto.JoinTeamDto;
import com.datealive.entity.dto.MessageDto;
import com.datealive.mapper.UserMapper;
import com.datealive.service.RedisService;
import com.datealive.utils.JacksonUtils;
import com.datealive.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: TeamMessage
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/17  19:42
 */
@Service
public class TeamMessageServiceImpl implements TeamMessageService{

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<MessageDto> getMessageDtoWithInvite(Long userId) {
        //用户查看邀请的信息 根据userId去查找自己的所有信息
        //邀请里有rateID teamID 和 自己的userID 所以需要前面在发送邀请的时候 缓存一个以userID为key的map 里面存放邀请信息
        List<MessageDto> list=new ArrayList<>();
        Map<Object, Object> hmget = redisUtil.hmget(StaticFinalCode.User_INVITE_MESSAGE_Key + ":" + userId);
        List<Object> joinTeamDtos = hmget.values()
                                         .stream()
                                         .collect(Collectors.toList());

        Iterator<Object> iterator = joinTeamDtos.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if(obj instanceof JoinTeamDto){
                JoinTeamDto joinTeamDto =(JoinTeamDto) obj;
                MessageDto messageDto = new MessageDto();
                messageDto.setTeamID(joinTeamDto.getTeamID());
                messageDto.setFromUserID(joinTeamDto.getFromUserID());
                messageDto.setToUserID(joinTeamDto.getToUserId());
                messageDto.setRateID(joinTeamDto.getRateID());
                messageDto.setOpenID(userMapper.findOpenIdByUserId(joinTeamDto.getFromUserID()));
                messageDto.setTitle("团队:"+joinTeamDto.getTeamName());
                messageDto.setInfo("用户"+joinTeamDto.getFromUserName()+"邀请你一起参加"+joinTeamDto.getRateTitle());
                messageDto.setFromUserAvatar(joinTeamDto.getFromUserAvatar());
                messageDto.setTime(joinTeamDto.getTime());
                list.add(messageDto);
            }
        }

        return list;
    }

    @Override
    public List<MessageDto> getMessageDtoWithApply(Long userId) {
        //用户查询 申请 加入团队的信息，根据userID查看别人申请加入自己团队的信息
        List<MessageDto> list=new ArrayList<>();
        Map<Object, Object> hmget = redisUtil.hmget(StaticFinalCode.User_APPLY_MESSAGE_Key + ":" + userId);
        List<Object> joinTeamDtos = hmget.values()
                .stream()
                .collect(Collectors.toList());

        Iterator<Object> iterator = joinTeamDtos.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if(obj instanceof JoinTeamDto) {
                JoinTeamDto joinTeamDto = (JoinTeamDto) obj;
                MessageDto messageDto = new MessageDto();
                messageDto.setTeamID(joinTeamDto.getTeamID());
                messageDto.setFromUserID(joinTeamDto.getFromUserID());
                messageDto.setRateID(joinTeamDto.getRateID());
                messageDto.setToUserID(joinTeamDto.getToUserId());
                messageDto.setOpenID(userMapper.findOpenIdByUserId(joinTeamDto.getFromUserID()));
                messageDto.setTitle(joinTeamDto.getFromUserName());
                messageDto.setInfo("用户" + joinTeamDto.getFromUserName() + "申请加入" + joinTeamDto.getTeamName() + "团队");
                messageDto.setFromUserAvatar(joinTeamDto.getFromUserAvatar());
                messageDto.setTime(joinTeamDto.getTime());
                list.add(messageDto);
            }
        }
        return list;
    }
}

package com.datealive.service.Impl.team.Request;

import com.datealive.common.StaticFinalCode;
import com.datealive.entity.dto.JoinTeamDto;
import com.datealive.service.RedisService;
import com.datealive.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName: 队长邀请JoinTeamByInvite
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/16  17:53
 */
@Service
@Slf4j
public class JoinTeamByInvite extends JoinTeamTemplate{


    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisService redisService;


    @Override
    public void saveRequestOnRedis(JoinTeamDto joinTeamDto) {
        joinTeamDto.setTime(new Date());
        //队长邀请其他人进入队伍 邀请信息先缓存在redis 2 天 这里存的map里的key为比赛id+teamid+userid 一个比赛一个人只能参加一次
        if (redisUtil.hHasKey(StaticFinalCode.Captain_INVITE_Key,
                joinTeamDto.getRateID()+":"+ joinTeamDto.getTeamID()+":"+ joinTeamDto.getToUserId())) {
            log.info("邀请了多次");
        }else{
            redisService.hsetJoinTeamRequestKey(StaticFinalCode.Captain_INVITE_Key,
                    joinTeamDto.getRateID()+":"+ joinTeamDto.getTeamID()+":"+ joinTeamDto.getToUserId(),
                    joinTeamDto,
                    StaticFinalCode.INVITE_TIMEOUT);
            redisService.hsetInviteMessage(StaticFinalCode.User_INVITE_MESSAGE_Key+":"+joinTeamDto.getToUserId(),
                            joinTeamDto.getRateID()+":"+ joinTeamDto.getTeamID()+":"+ joinTeamDto.getToUserId(),
                                  joinTeamDto,StaticFinalCode.INVITE_TIMEOUT);
        }
    }

}

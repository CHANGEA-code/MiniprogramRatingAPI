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
 * @ClassName: JoinTeamByApply
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/16  17:53
 */
@Service
@Slf4j
public class JoinTeamByApply extends JoinTeamTemplate{
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisService redisService;


    @Override
    public void saveRequestOnRedis(JoinTeamDto joinTeamDto) {
        joinTeamDto.setTime(new Date());
        //队员申请进入团队 redis缓存有效期设置为 1 天
        if (redisUtil.hHasKey(StaticFinalCode.User_APPLY_TEAM_Key,
                joinTeamDto.getRateID()+":"+ joinTeamDto.getTeamID()+":"+ joinTeamDto.getFromUserID())) {
            log.info("申请了多次");
        }else{
            redisService.hsetJoinTeamRequestKey(StaticFinalCode.User_APPLY_TEAM_Key,
                    joinTeamDto.getRateID()+":"+ joinTeamDto.getTeamID()+":"+ joinTeamDto.getFromUserID(),
                    joinTeamDto,
                    StaticFinalCode.APPLY_TIMEOUT);
            redisService.hsetApplyMessage(StaticFinalCode.User_APPLY_MESSAGE_Key+":"+joinTeamDto.getToUserId(),
                    joinTeamDto.getRateID()+":"+ joinTeamDto.getTeamID()+":"+ joinTeamDto.getFromUserID(),
                    joinTeamDto,
                    StaticFinalCode.APPLY_TIMEOUT);
        }

    }


}

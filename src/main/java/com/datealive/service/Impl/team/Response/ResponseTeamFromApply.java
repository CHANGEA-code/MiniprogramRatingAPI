package com.datealive.service.Impl.team.Response;

import com.datealive.common.StaticFinalCode;
import com.datealive.entity.dto.ResopnseTeamDto;
import com.datealive.entity.pojo.Teammate;
import com.datealive.service.RedisService;
import com.datealive.service.TeammateService;
import com.datealive.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ResponseTeamFromApply
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/17  19:23
 */
@Service
public class ResponseTeamFromApply extends ResponseTeamTemplate{


    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisService redisService;

    @Autowired
    private TeammateService teammateService;

    @Override
    public void joinTeamWork(ResopnseTeamDto resopnseTeamDto) {
        //如果同意 则进行入队操作 在入队操作之前 需要进行判断缓存是否过期
        if (resopnseTeamDto.getIsAgree().equals(1)) {
            //如果此时缓存中有这个请求 说明可以入队
            if (redisUtil.hHasKey(StaticFinalCode.User_APPLY_TEAM_Key,
                    resopnseTeamDto.getRateID()+":"+ resopnseTeamDto.getTeamID()+":"+ resopnseTeamDto.getUserID())) {
                Teammate teammate = new Teammate();
                teammate.setTeamID(resopnseTeamDto.getTeamID());
                teammate.setUserID(resopnseTeamDto.getUserID());
                if (teammateService.joinInTeam(teammate)) {
                    redisService.hdelKeyAndItem(StaticFinalCode.User_APPLY_TEAM_Key,
                            resopnseTeamDto.getRateID()+":"+ resopnseTeamDto.getTeamID()+":"+ resopnseTeamDto.getUserID());
                    redisService.hdelKeyAndItem(StaticFinalCode.User_APPLY_MESSAGE_Key+":"+resopnseTeamDto.getDelUserID(),
                            resopnseTeamDto.getRateID()+":"+ resopnseTeamDto.getTeamID()+":"+ resopnseTeamDto.getUserID());
                }
            }
            return;

        }else {
            //如果不同意，则直接删除redis中的缓存
            redisService.hdelKeyAndItem(StaticFinalCode.User_APPLY_TEAM_Key,
                                        resopnseTeamDto.getRateID()+":"+ resopnseTeamDto.getTeamID()+":"+ resopnseTeamDto.getUserID());
            redisService.hdelKeyAndItem(StaticFinalCode.User_APPLY_MESSAGE_Key+":"+resopnseTeamDto.getDelUserID(),
                                        resopnseTeamDto.getRateID()+":"+ resopnseTeamDto.getTeamID()+":"+ resopnseTeamDto.getUserID());
        }
    }
}

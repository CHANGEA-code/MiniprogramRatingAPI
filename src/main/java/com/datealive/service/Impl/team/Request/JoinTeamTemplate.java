package com.datealive.service.Impl.team.Request;

import com.datealive.entity.dto.JoinTeamDto;
import com.datealive.entity.pojo.Teammate;
import com.datealive.service.TeammateService;
import com.datealive.wechat.entity.TemplateMessage;
import com.datealive.wechat.service.WxSubscribeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: 队员入队的抽象类 使用模板方法JoinTeamTemplate
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/16  16:03
 */
public abstract class JoinTeamTemplate {

    @Autowired
    private WxSubscribeMessage wxSubscribeMessage;

    public void run(JoinTeamDto joinTeamDto,TemplateMessage templateMessage){
        saveRequestOnRedis(joinTeamDto);
        sendWxMessage(templateMessage);
    }

    public abstract void saveRequestOnRedis(JoinTeamDto joinTeamDto);

    private void sendWxMessage(TemplateMessage templateMessage){
        wxSubscribeMessage.sendMessage(templateMessage);
    }


}

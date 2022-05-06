package com.datealive.service.Impl.team.Response;

import com.datealive.entity.dto.ResopnseTeamDto;
import com.datealive.wechat.entity.TemplateMessage;
import com.datealive.wechat.service.WxSubscribeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ResponseTeamTemplate
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/17  19:11
 */
public abstract class ResponseTeamTemplate {

    @Autowired
    private WxSubscribeMessage wxSubscribeMessage;

    public final void run(ResopnseTeamDto resopnseTeamDto, TemplateMessage templateMessage){
        joinTeamWork(resopnseTeamDto);
        sendMessage(templateMessage);
    }

    public abstract void joinTeamWork(ResopnseTeamDto resopnseTeamDto);

    public void sendMessage(TemplateMessage templateMessage){
        wxSubscribeMessage.sendMessage(templateMessage);
    }


}

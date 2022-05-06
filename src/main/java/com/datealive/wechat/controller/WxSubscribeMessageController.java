package com.datealive.wechat.controller;

import com.datealive.common.Result;
import com.datealive.wechat.entity.TemplateMessage;
import com.datealive.wechat.service.WxSubscribeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: WxSubscribeMessageController
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/7  23:56
 */
@RestController
@RequestMapping("/message")
public class WxSubscribeMessageController {

    @Autowired
    private WxSubscribeMessage wxSubscribeMessage;

    @PostMapping("/sendWxMessage")
    public Result sendWxMessage(@RequestBody TemplateMessage templateMessage){
        return wxSubscribeMessage.sendMessage(templateMessage);
    }



}

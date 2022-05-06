package com.datealive.wechat.service;

import com.datealive.common.Result;
import com.datealive.wechat.entity.TemplateMessage;

/**
 * @ClassName: WxSubscribeMessage
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/7  23:48
 */
public interface WxSubscribeMessage {

    public Result sendMessage(TemplateMessage templateMessage);

}

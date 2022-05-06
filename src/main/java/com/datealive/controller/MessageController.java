package com.datealive.controller;

import com.datealive.aop.PermissionCheck;
import com.datealive.common.Result;
import com.datealive.entity.dto.MessageDto;
import com.datealive.service.Impl.team.Message.TeamMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @ClassName: MessageController
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/18  20:31
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private TeamMessageService teamMessageService;

    @PermissionCheck
    @GetMapping("/getMessageDtoWithInvite/{userId}")
    public Result getMessageDtoWithInvite(@PathVariable("userId") Long userId){
        List<MessageDto> messageDtoWithInvite = teamMessageService.getMessageDtoWithInvite(userId);
        if (messageDtoWithInvite.isEmpty()) {
            return Result.error("数据为空");
        }else{
            return Result.success("查询成功",messageDtoWithInvite);
        }
    }

    @PermissionCheck
    @GetMapping("/getMessageDtoWithApply/{userId}")
    public Result getMessageDtoWithApply(@PathVariable("userId")Long userId){
        List<MessageDto> messageDtoWithApply = teamMessageService.getMessageDtoWithApply(userId);
        if (messageDtoWithApply.isEmpty()) {
            return Result.error("数据为空");
        }else{
            return Result.success("查询成功",messageDtoWithApply);
        }
    }
}

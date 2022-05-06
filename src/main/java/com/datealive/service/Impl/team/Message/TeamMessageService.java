package com.datealive.service.Impl.team.Message;

import com.datealive.entity.dto.MessageDto;

import java.util.List;

/**
 * @ClassName: TeamMessageService
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/18  9:31
 */
public interface TeamMessageService {

    /**
     * 获取别人邀请你加入队伍的信息列表
     * @return
     */
    public List<MessageDto> getMessageDtoWithInvite(Long userId);

    /**
     * 获取别人申请进入你队伍的信息列表
     * @return
     */
    public List<MessageDto> getMessageDtoWithApply(Long userId);
}

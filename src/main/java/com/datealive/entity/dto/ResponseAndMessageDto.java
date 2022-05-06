package com.datealive.entity.dto;

import com.datealive.wechat.entity.TemplateMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: ResponseAndMessageDto
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/18  20:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAndMessageDto implements Serializable {

    private ResopnseTeamDto resopnseTeamDto;

    private TemplateMessage templateMessage;
}

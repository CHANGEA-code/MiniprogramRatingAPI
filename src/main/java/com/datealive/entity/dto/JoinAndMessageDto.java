package com.datealive.entity.dto;

import com.datealive.wechat.entity.TemplateMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: JoinAndMessageDto
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/18  20:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinAndMessageDto implements Serializable {

    private JoinTeamDto joinTeamDto;

    private TemplateMessage templateMessage;
}

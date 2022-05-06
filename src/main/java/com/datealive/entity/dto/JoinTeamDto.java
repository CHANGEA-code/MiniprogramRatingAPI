package com.datealive.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: JoinTeamDto
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/16  18:12
 */
@Data
public class JoinTeamDto implements Serializable {

    private String teamName;
    private String rateTitle;
    private String fromUserName;
    private String fromUserAvatar;

    private Long teamID;
    private Long FromUserID;
    private Long toUserId;
    private Long rateID;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "MM-dd HH:mm",timezone = "GMT+8")
    private Date time;
}

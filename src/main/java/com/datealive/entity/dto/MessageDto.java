package com.datealive.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.connection.stream.StreamInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: MessageDto
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/17  19:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto implements Serializable {

    private String title;
    private String info;
    private String FromUserAvatar;

    private Long teamID;
    private Long FromUserID;
    private Long rateID;
    private Long toUserID;
    private String openID;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "MM-dd HH:mm",timezone = "GMT+8")
    private Date time;
}

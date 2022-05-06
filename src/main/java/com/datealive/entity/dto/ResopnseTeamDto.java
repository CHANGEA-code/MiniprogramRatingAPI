package com.datealive.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @ClassName: ResopnseTeamDto
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/17  19:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResopnseTeamDto implements Serializable {

    private Long teamID;
    private Long userID;
    private Long rateID;
    private Integer isAgree;
    private Long delUserID;
}

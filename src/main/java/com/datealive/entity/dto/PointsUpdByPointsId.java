package com.datealive.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: PointsUpdByPointsId
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/3  20:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointsUpdByPointsId implements Serializable {

    private Long pointsId;

    private Long pointsCnt;

}

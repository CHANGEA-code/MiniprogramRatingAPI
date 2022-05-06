package com.datealive.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: PointsUpdByUserId
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/3  20:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointsUpdByUserId implements Serializable {

    private Long pointsCnt;

    private Long gzhuUserId;
}

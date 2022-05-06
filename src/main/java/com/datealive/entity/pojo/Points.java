package com.datealive.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Points 积分表
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/2  22:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Points implements Serializable {

    private Long pointsId;

    private Long pointsCnt;


    private Long gzhuUserId;
}

package com.datealive.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Goods
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/3  20:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {

    private Long goodsId;

    private String goodsName;

    private Long goodsPrice;

    private Long goodsCnt;

    private String goodsBanner;

}

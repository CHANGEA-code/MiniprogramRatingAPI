package com.datealive.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: RateTopList
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/17  17:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateTopList implements Serializable {

    private Long id;
    private String banner;
    private String title;

}

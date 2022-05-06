package com.datealive.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Ad
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/4  14:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ad implements Serializable {


    private Long adId;
    private String adName;
    private String adUrl;
    private String adBanner;


}

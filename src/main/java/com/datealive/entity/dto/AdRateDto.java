package com.datealive.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: AdRateDto
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/5  15:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdRateDto implements Serializable {

    private Long adId;
    private String adName;
    private String adUrl;
    private String adBanner;

    private Long gzhuRateId;
}

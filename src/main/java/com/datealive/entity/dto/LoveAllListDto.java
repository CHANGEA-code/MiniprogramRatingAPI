package com.datealive.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoveAllListDto {
    private Long rateID;

    private Long loveID;

    private String rateTitle;

    private String rateHost;

    private String rateIntroduction;

    private String rateBannerUrl;

    private Long rateCnt;
}

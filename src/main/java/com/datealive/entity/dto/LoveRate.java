package com.datealive.entity.dto;

import com.datealive.controller.LoveController;
import com.datealive.entity.pojo.Love;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoveRate {
    private Love love;

    private Long rateID;

    private String title;

    private String bannerUrl;

    private String introduction;

    private Long rateCnt;
}

package com.datealive.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateAdRelation {
    private Long relationID;
    private Long adID;
    private Long rateID;
}

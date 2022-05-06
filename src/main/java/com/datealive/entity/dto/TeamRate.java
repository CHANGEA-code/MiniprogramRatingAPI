package com.datealive.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamRate {

    private Long teamID;

    private Long rateID;

    private Long captainID;

    private Integer cnt;

    private String name;

    private String introduction;

    private String avatarUrl;

    private String title;

    private Integer cntLimited;

}

package com.datealive.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CORBA.PRIVATE_MEMBER;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private Long teamID;
    private Long rateID;
    private Long captainID;
    private Integer cnt;
    private String name;
    private String introduction;
    private String avatarUrl;
    private Integer cntLimited;

}

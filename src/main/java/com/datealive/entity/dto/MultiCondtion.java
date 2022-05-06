package com.datealive.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiCondtion implements Serializable {

    private Integer isTeam;
    private String grade;
    private Long typeID;
}

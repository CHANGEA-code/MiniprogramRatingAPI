package com.datealive.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Love {
    private Long loveID;
    private Long rateID;
    private Long userID;
}

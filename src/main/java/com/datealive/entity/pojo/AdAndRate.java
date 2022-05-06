package com.datealive.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: AdAndRate
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/4  20:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdAndRate implements Serializable {

    private Long relationId;
    private Long gzhuAdId;
    private Long gzhuRateId;
}

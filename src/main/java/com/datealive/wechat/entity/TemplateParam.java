package com.datealive.wechat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: TemplateParam
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/9  17:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateParam implements Serializable {

    private String key;
    private String value;


}

package com.datealive.wechat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: TemplateMessage
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/9  17:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateMessage implements Serializable {

    /**
     * 小程序用户openID
     */
    private String touser;

    /**
     * 小程序订阅消息模板ID
     */
    private String template_id;

    /**
     * 点击跳转的页面
     */
    private String page;

    /**
     * 小程序订阅消息模板参数
     */
    private List<TemplateParam> data;


}

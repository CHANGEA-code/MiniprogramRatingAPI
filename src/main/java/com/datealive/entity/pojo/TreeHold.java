package com.datealive.entity.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeHold implements Serializable {
    /**
     * id是动态的ID号
     * context是动态的内容
     * createTime是动态发步的时间
     * userID是发步的人的ID
     */
    private Long id;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
    private Long userID;
}

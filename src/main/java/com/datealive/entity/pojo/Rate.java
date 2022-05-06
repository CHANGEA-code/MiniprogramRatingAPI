package com.datealive.entity.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Rate
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/5  20:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rate implements Serializable {
    private Long id;
    private String host;
    private String title;
    private String grade;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date applyStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date applyEnd;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date start;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date end;
    private String bannerUrl;
    private Integer isTeam;
    private Long  typeID;
    private String  content;
    private Integer price;
    private String  location;
    private Integer isOnline;
    private Integer countLimited;
    private Integer isCross;
    private Integer counts;
    private String introduction;
    private Integer canApply;

}

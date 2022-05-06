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
public class User implements Serializable{
    private Long id;
    //用户的ID
    private String username;
    //用户的用户名
    private String password;
    //用户的密码
    private String email;
    //用户的邮箱
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
    //用户的注册时间
    private String nickName;
    //用户的昵称
    private String avatarUrl;
    //用户的头像
    private Integer isindentify;
    //用户是否通过了实名认证
    private String phone;
    //用户的手机号码
    private String country;
    //用户国家
    private String province;
    //用户所处省份
    private String city;
    //用户所在城市
    private Integer gender;
    //用户的开放ID
    private String openID;

    private Long resumeId;
}

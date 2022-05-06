package com.datealive.entity.vo.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
/**
 *  UpdateUserDetail是一个vo，用来传递需要注册的基本信息
 *
 */
public class UpdateUserDetail implements Serializable {
    //更新user的详细信息，只能更新
    // username,用户名
    // password,密码
    // email,绑定邮箱
    // nickname,昵称
    // avatar,头像
    // phone 手机号码
    //因为id，是数据库自增的，所以不能修改，而实名验证状态也是不能修改，以及用户创建的时间也是不能被修改
    private Long id;
    //该用户ID，只能做输入，不能被修改
    private String username;
    //用户的用户名
    private String password;
    //用户的密码
    private String email;
    //用户的邮箱
    private String nickname;
    //用户的昵称
    private String avatarUrl;
    //用户的头像
    private String phone;
    //用户的手机号码
}

package com.datealive.wechat.entity;

import com.datealive.entity.pojo.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @ClassName: WxUserDto 微信小程序发送过来的用户信息传输实体类
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/7  15:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxUserDto implements Serializable {


    /**
     * 用户名
     */
    private String userName;

    /**
     * 传入参数：临时登录凭证
     */
    private String code;


    /**
     * 微信openId
     */
    private String openId;

    /**
     * 传入参数: 用户非敏感信息
     */
    private String rawData;

    /**
     * 传入参数: 签名
     */
    private String signature;

    /**
     * 传入参数: 用户敏感信息
     */
    private String encryptedData;

    /**
     * 传入参数: 解密算法的向量
     */
    private String iv;

    /**
     * 返回:服务器jwt token
     */
    private String token;

    /**
     * 返回：userName或openId对应的用户
     */
    private User user;




}

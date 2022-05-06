package com.datealive.wechat.service;

import com.datealive.wechat.entity.WxUserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: WxUserLoginService
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/7  15:59
 */
public interface WxUserLoginService {


    public WxUserDto UserLoginAndRegister(WxUserDto wxUserDto, HttpServletResponse response);


}

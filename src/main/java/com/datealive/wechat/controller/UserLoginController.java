package com.datealive.wechat.controller;

import com.datealive.common.Result;
import com.datealive.wechat.entity.WxUserDto;
import com.datealive.wechat.service.WxUserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


/**
 * @ClassName: UserLoginController
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/7  15:47
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserLoginController {


    @Autowired
    private WxUserLoginService wxUserLoginService;

    @PostMapping("/userLogin")
    public Result login(@RequestBody WxUserDto wxUserDto, HttpServletResponse response){
        WxUserDto wxUserDto1= wxUserLoginService.UserLoginAndRegister(wxUserDto, response);
        return Result.success("登录成功",wxUserDto1);

    }



}

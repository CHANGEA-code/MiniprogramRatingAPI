package com.datealive.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description: TODO
 * @author: datealive
 * @date: 2021/4/25  22:31
 */
@RestController
@RequestMapping("/admin")
public class TestController {

    @GetMapping("/test")
    public String test(){
        System.out.println("进入了test页面");
        return "test";
    }

}

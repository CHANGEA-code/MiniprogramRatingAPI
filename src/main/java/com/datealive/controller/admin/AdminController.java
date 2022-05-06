package com.datealive.controller.admin;

import com.datealive.common.PageResult;
import com.datealive.common.Result;
import com.datealive.service.Impl.RateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private RateServiceImpl rateService;

    @GetMapping("/getRateList")
    public PageResult getRateList(){
        return null;
    }
}

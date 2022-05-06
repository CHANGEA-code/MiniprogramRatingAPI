package com.datealive.controller;

import com.datealive.aop.PermissionCheck;
import com.datealive.common.Result;
import com.datealive.service.UpLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: UpLoadController
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/21  9:53
 */
@RestController
@RequestMapping("/upload")
public class UpLoadController {

    @Autowired
    private UpLoadService upLoadService;

    @PermissionCheck
    @PostMapping("/UpLoadImage")
    public Result UpLoadImage(HttpServletRequest request, MultipartFile file){
        return upLoadService.UpLoadImgToCos(request,file);
    }


}

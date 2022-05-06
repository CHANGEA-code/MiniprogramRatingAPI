package com.datealive.service;

import com.datealive.common.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: UpLoadService
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/21  9:54
 */
public interface UpLoadService {

    public Result UpLoadImg(HttpServletRequest request, MultipartFile file);

    public Result UpLoadImgToCos(HttpServletRequest request,MultipartFile file);
}

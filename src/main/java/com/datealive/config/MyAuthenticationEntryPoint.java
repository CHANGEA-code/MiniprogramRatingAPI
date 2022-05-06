package com.datealive.config;

import com.datealive.common.Result;
import com.datealive.common.ResultCode;
import com.datealive.utils.JacksonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName: MyAuthenticationEntryPoint
 * @Description: TODO
 * @author: datealive
 * @date: 2021/4/26  0:09
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Result result = Result.error(ResultCode.Forbidden, "请登录");
        out.write(JacksonUtils.writeValueAsString(result));
        out.flush();
        out.close();
    }
}
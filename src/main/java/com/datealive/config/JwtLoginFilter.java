package com.datealive.config;

import com.datealive.common.Result;
import com.datealive.common.ResultCode;
import com.datealive.entity.pojo.Manager;
import com.datealive.exception.BadRequestException;
import com.datealive.utils.JacksonUtils;
import com.datealive.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: JwtLoginFilter
 * @Description: TODO
 * @author: datealive
 * @date: 2021/4/26  0:11
 */
@Slf4j
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    ThreadLocal<String> currentUsername = new ThreadLocal<>();

    protected JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {
        try {
            Manager manager = JacksonUtils.readValue(request.getInputStream(), Manager.class);
            currentUsername.set(manager.getUsername());

            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(manager.getUsername(),manager.getPassword()));
        } catch (BadRequestException exception) {

            response.setContentType("application/json;charset=utf-8");
            Result result = Result.error(ResultCode.INVALID_REQUEST, "????????????");
            PrintWriter out = response.getWriter();
            out.write(JacksonUtils.writeValueAsString(result));
            out.flush();
            out.close();

        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {
        String jwt = JwtTokenUtils.generateToken(authResult.getName(), authResult.getAuthorities());

        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Authorization",jwt);
        Manager manager = (Manager) authResult.getPrincipal();
        manager.setPassword(null);
        Map<String, Object> map = new HashMap<>();
        map.put("manager", manager);
        map.put("token", jwt);
        Result result = Result.success("????????????", map);
        PrintWriter out = response.getWriter();
        out.write(JacksonUtils.writeValueAsString(result));
        out.flush();
        out.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException exception) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String msg = exception.getMessage();
        //?????????????????????????????????????????????
        if (exception instanceof LockedException) {
            msg = "???????????????";
        } else if (exception instanceof CredentialsExpiredException) {
            msg = "????????????";
        } else if (exception instanceof AccountExpiredException) {
            msg = "????????????";
        } else if (exception instanceof DisabledException) {
            msg = "???????????????";
        } else if (exception instanceof BadCredentialsException) {
            msg = "????????????????????????";
        }
        PrintWriter out = response.getWriter();
        out.write(JacksonUtils.writeValueAsString(Result.error(ResultCode.INVALID_REQUEST, msg)));
        out.flush();
        out.close();
    }


}

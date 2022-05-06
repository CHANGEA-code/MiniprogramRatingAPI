package com.datealive.config;

import com.datealive.common.Result;
import com.datealive.common.ResultCode;
import com.datealive.utils.JacksonUtils;
import com.datealive.utils.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @ClassName: JwtFilter
 * @Description: TODO
 * @author: datealive
 * @date: 2021/4/26  0:14
 */
public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //后台管理路径外的请求直接跳过
        if (!request.getRequestURI().startsWith("/admin")) {
            filterChain.doFilter(request, servletResponse);
            return;
        }
        String jwt = request.getHeader("Authorization");
        if (JwtTokenUtils.judgeTokenIsExist(jwt)) {
            try {
                Claims claims = JwtTokenUtils.getTokenBody(jwt);
                String username = claims.getSubject();
                List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(token);
            } catch (Exception e) {
                e.printStackTrace();
                response.setContentType("application/json;charset=utf-8");
                Result result = Result.error(ResultCode.Forbidden, "凭证已失效，请重新登录！");
                PrintWriter out = response.getWriter();
                out.write(JacksonUtils.writeValueAsString(result));
                out.flush();
                out.close();
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
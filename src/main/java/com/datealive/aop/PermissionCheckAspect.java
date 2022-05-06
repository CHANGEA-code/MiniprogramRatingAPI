package com.datealive.aop;

import com.datealive.common.Result;
import com.datealive.service.RedisService;
import com.datealive.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: PermissionCheckAspect
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/18  23:04
 */

@Aspect
@Component
public class PermissionCheckAspect {


    @Autowired
    private UserService userService;
    /**
     * Spring中使用@Pointcut注解来定义方法切入点
     * @Pointcut 用来定义切点，针对方法  @Aspect 用来定义切面，针对类
     * 后面的增强均是围绕此切入点来完成的
     * 此处仅配置被我们刚才定义的注解：permissionCheckCut
     *
     */
    @Pointcut("@annotation(com.datealive.aop.PermissionCheck)")
    private void permissionCheckCut(){};

    /**
     * 定义了切面的处理逻辑。即方法上加了@PermissionCheck
     * 使用了环绕增强 在方法执行前后均会执行
     */

    @Around("permissionCheckCut()")
    public Object doPermissionCheck(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String openId = request.getHeader("Authorization");
        String userId = request.getHeader("UserId");
        //如果userid和openid为空 则返回false
        if (openId==null||userId==null) {
            return Result.error("header参数不能为空");
        }else {
            //如果权限为true ，则放行
            if (userService.findOpenIdByUserId(Long.valueOf(userId), openId)) {
                return pjp.proceed();
            }
            return Result.error("权限不足");
        }


    }



}

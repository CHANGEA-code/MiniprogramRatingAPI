package com.datealive.aop;

import java.lang.annotation.*;

/**
 * @ClassName: PermissionCheck
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/18  23:02
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface PermissionCheck {

    public String value() default "";
}

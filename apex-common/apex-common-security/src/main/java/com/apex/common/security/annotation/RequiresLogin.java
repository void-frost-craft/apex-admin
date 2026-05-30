package com.apex.common.security.annotation;

import java.lang.annotation.*;

/**
 * 登录检查注解
 *
 * @author apex
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresLogin {
}

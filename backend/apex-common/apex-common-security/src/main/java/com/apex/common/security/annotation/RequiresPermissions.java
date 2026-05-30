package com.apex.common.security.annotation;

import java.lang.annotation.*;

/**
 * 权限检查注解
 *
 * @author apex
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresPermissions {

    /**
     * 权限标识
     */
    String[] value();

    /**
     * 逻辑类型
     */
    Logical logical() = Logical.AND;

    enum Logical {
        AND, OR
    }
}

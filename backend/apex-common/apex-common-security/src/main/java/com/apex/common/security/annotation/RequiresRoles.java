package com.apex.common.security.annotation;

import java.lang.annotation.*;

/**
 * 角色检查注解
 *
 * @author apex
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresRoles {

    /**
     * 角色标识
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

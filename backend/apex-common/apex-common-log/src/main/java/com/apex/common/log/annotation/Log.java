package com.apex.common.log.annotation;

import com.apex.common.log.enums.BusinessType;

import java.lang.annotation.*;

/**
 * 自定义操作日志注解
 *
 * @author apex
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块名称
     */
    String title() default "";

    /**
     * 业务类型
     */
    BusinessType businessType() = BusinessType.OTHER;

    /**
     * 是否保存请求数据
     */
    boolean isSaveRequestData() default true;

    /**
     * 是否保存响应数据
     */
    boolean isSaveResponseData() default true;
}

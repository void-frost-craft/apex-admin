package com.apex.common.security.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.apex.common.core.domain.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器（安全）
 *
 * @author apex
 */
@Order(-1)
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 未登录异常
     */
    @ExceptionHandler(NotLoginException.class)
    public R<?> handleNotLoginException(NotLoginException e) {
        log.error("未登录: {}", e.getMessage());
        return R.unauthorized("未登录或登录已过期");
    }

    /**
     * 无权限异常
     */
    @ExceptionHandler(NotPermissionException.class)
    public R<?> handleNotPermissionException(NotPermissionException e) {
        log.error("无权限: {}", e.getMessage());
        return R.forbidden("没有访问权限");
    }

    /**
     * 无角色异常
     */
    @ExceptionHandler(NotRoleException.class)
    public R<?> handleNotRoleException(NotRoleException e) {
        log.error("无角色: {}", e.getMessage());
        return R.forbidden("没有访问权限");
    }
}

package com.apex.common.security.aspect;

import cn.dev33.satoken.stp.StpUtil;
import com.apex.common.core.exception.BusinessException;
import com.apex.common.security.annotation.RequiresPermissions;
import com.apex.common.security.annotation.RequiresRoles;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 权限检查切面
 *
 * @author apex
 */
@Aspect
@Component
public class PermissionAspect {

    /**
     * 权限检查
     */
    @Around("@annotation(requiresPermissions)")
    public Object checkPermission(ProceedingJoinPoint joinPoint, RequiresPermissions requiresPermissions) throws Throwable {
        String[] permissions = requiresPermissions.value();
        RequiresPermissions.Logical logical = requiresPermissions.logical();

        if (logical == RequiresPermissions.Logical.AND) {
            StpUtil.checkPermissionAnd(permissions);
        } else {
            StpUtil.checkPermissionOr(permissions);
        }

        return joinPoint.proceed();
    }

    /**
     * 角色检查
     */
    @Around("@annotation(requiresRoles)")
    public Object checkRole(ProceedingJoinPoint joinPoint, RequiresRoles requiresRoles) throws Throwable {
        String[] roles = requiresRoles.value();
        RequiresRoles.Logical logical = requiresRoles.logical();

        if (logical == RequiresRoles.Logical.AND) {
            StpUtil.checkRoleAnd(roles);
        } else {
            StpUtil.checkRoleOr(roles);
        }

        return joinPoint.proceed();
    }
}

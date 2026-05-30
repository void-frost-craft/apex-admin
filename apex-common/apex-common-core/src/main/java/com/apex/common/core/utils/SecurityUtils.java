package com.apex.common.core.utils;

import cn.dev33.satoken.stp.StpUtil;

/**
 * 安全工具类
 *
 * @author apex
 */
public class SecurityUtils {

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        try {
            return StpUtil.getLoginIdAsLong();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取用户名
     */
    public static String getUsername() {
        try {
            return (String) StpUtil.getExtra("username");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取用户昵称
     */
    public static String getNickname() {
        try {
            return (String) StpUtil.getExtra("nickname");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断是否已登录
     */
    public static boolean isLogin() {
        try {
            return StpUtil.isLogin();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断是否管理员
     */
    public static boolean isAdmin() {
        try {
            Long userId = getUserId();
            return userId != null && userId == 1L;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 检查权限
     */
    public static boolean hasPermission(String permission) {
        try {
            return StpUtil.hasPermission(permission);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 检查角色
     */
    public static boolean hasRole(String role) {
        try {
            return StpUtil.hasRole(role);
        } catch (Exception e) {
            return false;
        }
    }
}

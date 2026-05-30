package com.apex.auth.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.apex.auth.domain.dto.LoginDTO;
import com.apex.auth.domain.dto.RegisterDTO;
import com.apex.auth.domain.vo.LoginVO;
import com.apex.auth.service.AuthService;
import com.apex.common.core.exception.BusinessException;
import com.apex.common.core.utils.SecurityUtils;
import com.apex.common.redis.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 认证服务实现
 *
 * @author apex
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RedisUtils redisUtils;

    /**
     * 用户登录
     */
    @Override
    public LoginVO login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        // TODO: 查询用户信息
        // 这里暂时使用模拟数据，后续集成系统管理服务
        if (!"admin".equals(username) || !"admin123".equals(password)) {
            throw new BusinessException("用户名或密码错误");
        }

        // 模拟用户信息
        Long userId = 1L;
        String nickname = "超级管理员";
        String avatar = "";
        String email = "admin@apex.com";
        String phone = "15888888888";
        Integer sex = 0;
        String deptName = "研发部门";

        // Sa-Token 登录
        StpUtil.login(userId);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        // 存储用户信息到 Redis
        String userKey = "user:info:" + userId;
        redisUtils.set(userKey, username, 30 * 24 * 60 * 60, java.util.concurrent.TimeUnit.SECONDS);

        // 构建返回结果
        List<String> roles = new ArrayList<>();
        roles.add("admin");

        List<String> permissions = new ArrayList<>();
        permissions.add("system:user:list");
        permissions.add("system:user:add");
        permissions.add("system:user:edit");
        permissions.add("system:user:remove");
        permissions.add("system:role:list");
        permissions.add("system:menu:list");
        permissions.add("system:dept:list");
        permissions.add("system:post:list");

        return LoginVO.builder()
                .accessToken(tokenInfo.getTokenValue())
                .refreshToken(tokenInfo.getTokenValue())
                .tokenType("Bearer")
                .expiresIn(tokenInfo.getTokenTimeout())
                .userId(userId)
                .username(username)
                .nickname(nickname)
                .avatar(avatar)
                .email(email)
                .phone(phone)
                .sex(sex)
                .deptName(deptName)
                .roles(roles)
                .permissions(permissions)
                .build();
    }

    /**
     * 用户注册
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterDTO registerDTO) {
        String username = registerDTO.getUsername();
        String password = registerDTO.getPassword();
        String confirmPassword = registerDTO.getConfirmPassword();

        // 验证两次密码是否一致
        if (!password.equals(confirmPassword)) {
            throw new BusinessException("两次输入的密码不一致");
        }

        // TODO: 检查用户名是否已存在
        // 这里暂时使用模拟逻辑
        if ("admin".equals(username)) {
            throw new BusinessException("用户名已存在");
        }

        // TODO: 保存用户信息到数据库
        // 这里暂时只记录日志
        log.info("用户注册成功: {}", username);

        // TODO: 调用系统管理服务保存用户
        // systemService.createUser(user);
    }

    /**
     * 退出登录
     */
    @Override
    public void logout() {
        Long userId = SecurityUtils.getUserId();
        if (userId != null) {
            // 删除用户信息缓存
            String userKey = "user:info:" + userId;
            redisUtils.delete(userKey);

            // Sa-Token 退出登录
            StpUtil.logout();
            log.info("用户退出登录: {}", userId);
        }
    }

    /**
     * 刷新 Token
     */
    @Override
    public LoginVO refreshToken() {
        Long userId = SecurityUtils.getUserId();
        if (userId == null) {
            throw new BusinessException("用户未登录");
        }

        // 重新登录，获取新的 Token
        StpUtil.login(userId);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        // 获取用户信息
        return getUserInfo();
    }

    /**
     * 获取当前用户信息
     */
    @Override
    public LoginVO getUserInfo() {
        Long userId = SecurityUtils.getUserId();
        if (userId == null) {
            throw new BusinessException("用户未登录");
        }

        // TODO: 从数据库查询用户信息
        // 这里暂时使用模拟数据
        String username = SecurityUtils.getUsername();
        if (username == null) {
            username = "admin";
        }

        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        List<String> roles = new ArrayList<>();
        roles.add("admin");

        List<String> permissions = new ArrayList<>();
        permissions.add("system:user:list");
        permissions.add("system:user:add");
        permissions.add("system:user:edit");
        permissions.add("system:user:remove");
        permissions.add("system:role:list");
        permissions.add("system:menu:list");
        permissions.add("system:dept:list");
        permissions.add("system:post:list");

        return LoginVO.builder()
                .accessToken(tokenInfo.getTokenValue())
                .refreshToken(tokenInfo.getTokenValue())
                .tokenType("Bearer")
                .expiresIn(tokenInfo.getTokenTimeout())
                .userId(userId)
                .username(username)
                .nickname("超级管理员")
                .avatar("")
                .email("admin@apex.com")
                .phone("15888888888")
                .sex(0)
                .deptName("研发部门")
                .roles(roles)
                .permissions(permissions)
                .build();
    }

    /**
     * 修改密码
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(String oldPassword, String newPassword) {
        Long userId = SecurityUtils.getUserId();
        if (userId == null) {
            throw new BusinessException("用户未登录");
        }

        // TODO: 验证旧密码
        // 这里暂时使用模拟逻辑
        if (!"admin123".equals(oldPassword)) {
            throw new BusinessException("旧密码错误");
        }

        // TODO: 更新数据库中的密码
        // 这里暂时只记录日志
        log.info("用户 {} 修改密码成功", userId);
    }
}

package com.apex.auth.service;

import com.apex.auth.domain.dto.LoginDTO;
import com.apex.auth.domain.dto.RegisterDTO;
import com.apex.auth.domain.vo.LoginVO;

/**
 * 认证服务接口
 *
 * @author apex
 */
public interface AuthService {

    /**
     * 用户登录
     *
     * @param loginDTO 登录参数
     * @return 登录结果
     */
    LoginVO login(LoginDTO loginDTO);

    /**
     * 用户注册
     *
     * @param registerDTO 注册参数
     */
    void register(RegisterDTO registerDTO);

    /**
     * 退出登录
     */
    void logout();

    /**
     * 刷新 Token
     *
     * @return 登录结果
     */
    LoginVO refreshToken();

    /**
     * 获取当前用户信息
     *
     * @return 用户信息
     */
    LoginVO getUserInfo();

    /**
     * 修改密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void updatePassword(String oldPassword, String newPassword);
}

package com.apex.auth.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 登录响应结果
 *
 * @author apex
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "登录响应结果")
public class LoginVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "访问令牌", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String accessToken;

    @Schema(description = "刷新令牌", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String refreshToken;

    @Schema(description = "令牌类型", example = "Bearer")
    private String tokenType;

    @Schema(description = "过期时间（秒）", example = "2592000")
    private Long expiresIn;

    @Schema(description = "用户ID", example = "1")
    private Long userId;

    @Schema(description = "用户名", example = "admin")
    private String username;

    @Schema(description = "用户昵称", example = "超级管理员")
    private String nickname;

    @Schema(description = "用户头像", example = "https://example.com/avatar.jpg")
    private String avatar;

    @Schema(description = "用户邮箱", example = "admin@example.com")
    private String email;

    @Schema(description = "用户手机号", example = "13800138000")
    private String phone;

    @Schema(description = "性别 0=男 1=女 2=未知", example = "0")
    private Integer sex;

    @Schema(description = "部门名称", example = "研发部门")
    private String deptName;

    @Schema(description = "角色列表", example = "[\"admin\", \"user\"]")
    private List<String> roles;

    @Schema(description = "权限列表", example = "[\"system:user:list\", \"system:user:add\"]")
    private List<String> permissions;
}

package com.apex.system.domain.dto;

import com.apex.common.core.domain.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 用户请求参数
 *
 * @author apex
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户请求参数")
public class UserDTO extends PageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户ID")
    private Long userId;

    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 20, message = "用户名长度必须在 2-20 之间")
    @Schema(description = "用户名", required = true, example = "newuser")
    private String username;

    @Size(min = 6, max = 20, message = "密码长度必须在 6-20 之间")
    @Schema(description = "密码", example = "password123")
    private String password;

    @NotBlank(message = "用户昵称不能为空")
    @Size(max = 20, message = "昵称长度不能超过 20")
    @Schema(description = "用户昵称", required = true, example = "新用户")
    private String nickname;

    @Email(message = "邮箱格式不正确")
    @Schema(description = "邮箱", example = "user@example.com")
    private String email;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    @Schema(description = "手机号", example = "13800138000")
    private String phone;

    @Schema(description = "性别 0=男 1=女 2=未知", example = "0")
    private Integer sex;

    @Schema(description = "头像地址", example = "https://example.com/avatar.jpg")
    private String avatar;

    @Schema(description = "状态 0=正常 1=停用", example = "0")
    private Integer status;

    @Schema(description = "部门ID", example = "103")
    private Long deptId;

    @Schema(description = "岗位ID列表", example = "[1, 2]")
    private List<Long> postIds;

    @Schema(description = "角色ID列表", example = "[1, 2]")
    private List<Long> roleIds;

    @Schema(description = "备注", example = "备注信息")
    private String remark;
}

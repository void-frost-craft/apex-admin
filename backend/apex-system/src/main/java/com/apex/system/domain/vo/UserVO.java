package com.apex.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户响应结果
 *
 * @author apex
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户响应结果")
public class UserVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户ID", example = "1")
    private Long userId;

    @Schema(description = "用户名", example = "admin")
    private String username;

    @Schema(description = "用户昵称", example = "超级管理员")
    private String nickname;

    @Schema(description = "邮箱", example = "admin@example.com")
    private String email;

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

    @Schema(description = "部门名称", example = "研发部门")
    private String deptName;

    @Schema(description = "岗位ID列表", example = "[1, 2]")
    private List<Long> postIds;

    @Schema(description = "岗位名称列表", example = "[\"董事长\", \"技术总监\"]")
    private List<String> postNames;

    @Schema(description = "角色ID列表", example = "[1, 2]")
    private List<Long> roleIds;

    @Schema(description = "角色名称列表", example = "[\"超级管理员\", \"普通角色\"]")
    private List<String> roleNames;

    @Schema(description = "最后登录IP", example = "127.0.0.1")
    private String loginIp;

    @Schema(description = "最后登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginDate;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "备注", example = "备注信息")
    private String remark;
}

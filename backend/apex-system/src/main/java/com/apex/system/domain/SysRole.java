package com.apex.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色实体
 *
 * @author apex
 */
@Data
@TableName("sys_role")
public class SysRole implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 角色ID */
    @TableId(type = IdType.AUTO)
    private Long roleId;

    /** 角色名称 */
    private String roleName;

    /** 角色权限字符串 */
    private String roleKey;

    /** 显示顺序 */
    private Integer roleSort;

    /** 数据范围 1=全部数据 2=自定义数据 3=本部门数据 4=本部门及以下数据 5=仅本人数据 */
    private Integer dataScope;

    /** 菜单树选择项是否关联 0=不关联 1=关联 */
    private Integer menuCheckStrictly;

    /** 部门树选择项是否关联 0=不关联 1=关联 */
    private Integer deptCheckStrictly;

    /** 状态 0=正常 1=停用 */
    private Integer status;

    /** 删除标志 0=存在 2=删除 */
    @TableLogic
    private Integer delFlag;

    /** 备注 */
    private String remark;

    /** 创建者 */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /** 更新者 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}

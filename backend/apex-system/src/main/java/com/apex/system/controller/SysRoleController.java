package com.apex.system.controller;

import com.apex.common.core.domain.PageResult;
import com.apex.common.core.domain.R;
import com.apex.common.log.annotation.Log;
import com.apex.common.log.enums.BusinessType;
import com.apex.common.security.annotation.RequiresPermissions;
import com.apex.system.domain.SysRole;
import com.apex.system.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理控制器
 *
 * @author apex
 */
@Tag(name = "角色管理", description = "角色的增删改查")
@RestController
@RequestMapping("/system/role")
@RequiredArgsConstructor
public class SysRoleController {

    private final SysRoleService roleService;

    /**
     * 获取角色列表
     */
    @Operation(summary = "获取角色列表")
    @RequiresPermissions("system:role:list")
    @GetMapping("/list")
    public R<PageResult<SysRole>> list(SysRole role,
                                        @RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult<SysRole> result = roleService.selectRoleList(role, pageNum, pageSize);
        return R.ok(result);
    }

    /**
     * 获取角色详情
     */
    @Operation(summary = "获取角色详情")
    @RequiresPermissions("system:role:query")
    @GetMapping("/{roleId}")
    public R<SysRole> getInfo(@PathVariable Long roleId) {
        SysRole role = roleService.selectRoleById(roleId);
        return R.ok(role);
    }

    /**
     * 新增角色
     */
    @Operation(summary = "新增角色")
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:role:add")
    @PostMapping
    public R<Long> add(@Valid @RequestBody SysRole role) {
        Long roleId = roleService.insertRole(role);
        return R.ok(roleId);
    }

    /**
     * 修改角色
     */
    @Operation(summary = "修改角色")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:role:edit")
    @PutMapping
    public R<Void> edit(@Valid @RequestBody SysRole role) {
        roleService.updateRole(role);
        return R.ok();
    }

    /**
     * 删除角色
     */
    @Operation(summary = "删除角色")
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:role:remove")
    @DeleteMapping("/{roleIds}")
    public R<Void> remove(@PathVariable List<Long> roleIds) {
        roleService.deleteRoleByIds(roleIds);
        return R.ok();
    }

    /**
     * 修改角色状态
     */
    @Operation(summary = "修改角色状态")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:role:edit")
    @PutMapping("/changeStatus")
    public R<Void> changeStatus(@RequestParam Long roleId, @RequestParam Integer status) {
        roleService.updateRoleStatus(roleId, status);
        return R.ok();
    }

    /**
     * 校验角色名称是否唯一
     */
    @Operation(summary = "校验角色名称是否唯一")
    @GetMapping("/checkRoleNameUnique")
    public R<Boolean> checkRoleNameUnique(@RequestParam String roleName) {
        boolean unique = roleService.checkRoleNameUnique(roleName);
        return R.ok(unique);
    }

    /**
     * 校验角色权限是否唯一
     */
    @Operation(summary = "校验角色权限是否唯一")
    @GetMapping("/checkRoleKeyUnique")
    public R<Boolean> checkRoleKeyUnique(@RequestParam String roleKey) {
        boolean unique = roleService.checkRoleKeyUnique(roleKey);
        return R.ok(unique);
    }
}

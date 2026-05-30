package com.apex.system.controller;

import com.apex.common.core.domain.R;
import com.apex.common.log.annotation.Log;
import com.apex.common.log.enums.BusinessType;
import com.apex.common.security.annotation.RequiresPermissions;
import com.apex.system.domain.SysDept;
import com.apex.system.service.SysDeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理控制器
 *
 * @author apex
 */
@Tag(name = "部门管理", description = "部门的增删改查")
@RestController
@RequestMapping("/system/dept")
@RequiredArgsConstructor
public class SysDeptController {

    private final SysDeptService deptService;

    /**
     * 获取部门列表
     */
    @Operation(summary = "获取部门列表")
    @RequiresPermissions("system:dept:list")
    @GetMapping("/list")
    public R<List<SysDept>> list(SysDept dept) {
        List<SysDept> depts = deptService.selectDeptList(dept);
        return R.ok(depts);
    }

    /**
     * 获取部门树
     */
    @Operation(summary = "获取部门树")
    @RequiresPermissions("system:dept:list")
    @GetMapping("/tree")
    public R<List<SysDept>> tree(SysDept dept) {
        List<SysDept> depts = deptService.selectDeptTree(dept);
        return R.ok(depts);
    }

    /**
     * 获取部门详情
     */
    @Operation(summary = "获取部门详情")
    @RequiresPermissions("system:dept:query")
    @GetMapping("/{deptId}")
    public R<SysDept> getInfo(@PathVariable Long deptId) {
        SysDept dept = deptService.selectDeptById(deptId);
        return R.ok(dept);
    }

    /**
     * 新增部门
     */
    @Operation(summary = "新增部门")
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dept:add")
    @PostMapping
    public R<Long> add(@Valid @RequestBody SysDept dept) {
        Long deptId = deptService.insertDept(dept);
        return R.ok(deptId);
    }

    /**
     * 修改部门
     */
    @Operation(summary = "修改部门")
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dept:edit")
    @PutMapping
    public R<Void> edit(@Valid @RequestBody SysDept dept) {
        deptService.updateDept(dept);
        return R.ok();
    }

    /**
     * 删除部门
     */
    @Operation(summary = "删除部门")
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dept:remove")
    @DeleteMapping("/{deptId}")
    public R<Void> remove(@PathVariable Long deptId) {
        deptService.deleteDeptById(deptId);
        return R.ok();
    }

    /**
     * 校验部门名称是否唯一
     */
    @Operation(summary = "校验部门名称是否唯一")
    @GetMapping("/checkDeptNameUnique")
    public R<Boolean> checkDeptNameUnique(@RequestParam String deptName, @RequestParam Long parentId) {
        boolean unique = deptService.checkDeptNameUnique(deptName, parentId);
        return R.ok(unique);
    }
}

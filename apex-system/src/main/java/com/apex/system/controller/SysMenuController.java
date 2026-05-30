package com.apex.system.controller;

import com.apex.common.core.domain.R;
import com.apex.common.log.annotation.Log;
import com.apex.common.log.enums.BusinessType;
import com.apex.common.security.annotation.RequiresPermissions;
import com.apex.system.domain.SysMenu;
import com.apex.system.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 菜单管理控制器
 *
 * @author apex
 */
@Tag(name = "菜单管理", description = "菜单的增删改查")
@RestController
@RequestMapping("/system/menu")
@RequiredArgsConstructor
public class SysMenuController {

    private final SysMenuService menuService;

    /**
     * 获取菜单列表
     */
    @Operation(summary = "获取菜单列表")
    @RequiresPermissions("system:menu:list")
    @GetMapping("/list")
    public R<List<SysMenu>> list(SysMenu menu) {
        List<SysMenu> menus = menuService.selectMenuList(menu);
        return R.ok(menus);
    }

    /**
     * 获取菜单树
     */
    @Operation(summary = "获取菜单树")
    @RequiresPermissions("system:menu:list")
    @GetMapping("/tree")
    public R<List<SysMenu>> tree(SysMenu menu) {
        List<SysMenu> menus = menuService.selectMenuTree(menu);
        return R.ok(menus);
    }

    /**
     * 获取菜单详情
     */
    @Operation(summary = "获取菜单详情")
    @RequiresPermissions("system:menu:query")
    @GetMapping("/{menuId}")
    public R<SysMenu> getInfo(@PathVariable Long menuId) {
        SysMenu menu = menuService.selectMenuById(menuId);
        return R.ok(menu);
    }

    /**
     * 新增菜单
     */
    @Operation(summary = "新增菜单")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:menu:add")
    @PostMapping
    public R<Long> add(@Valid @RequestBody SysMenu menu) {
        Long menuId = menuService.insertMenu(menu);
        return R.ok(menuId);
    }

    /**
     * 修改菜单
     */
    @Operation(summary = "修改菜单")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:menu:edit")
    @PutMapping
    public R<Void> edit(@Valid @RequestBody SysMenu menu) {
        menuService.updateMenu(menu);
        return R.ok();
    }

    /**
     * 删除菜单
     */
    @Operation(summary = "删除菜单")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:menu:remove")
    @DeleteMapping("/{menuId}")
    public R<Void> remove(@PathVariable Long menuId) {
        menuService.deleteMenuById(menuId);
        return R.ok();
    }

    /**
     * 根据用户ID获取菜单树
     */
    @Operation(summary = "根据用户ID获取菜单树")
    @GetMapping("/user/{userId}")
    public R<List<SysMenu>> getUserMenuTree(@PathVariable Long userId) {
        List<SysMenu> menus = menuService.selectMenuListByUserId(userId);
        return R.ok(menus);
    }

    /**
     * 根据用户ID获取权限列表
     */
    @Operation(summary = "根据用户ID获取权限列表")
    @GetMapping("/perms/{userId}")
    public R<Set<String>> getUserPermissions(@PathVariable Long userId) {
        Set<String> perms = menuService.selectMenuPermsByUserId(userId);
        return R.ok(perms);
    }
}

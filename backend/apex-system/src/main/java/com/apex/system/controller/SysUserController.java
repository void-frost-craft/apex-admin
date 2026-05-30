package com.apex.system.controller;

import com.apex.common.core.domain.PageResult;
import com.apex.common.core.domain.R;
import com.apex.common.log.annotation.Log;
import com.apex.common.log.enums.BusinessType;
import com.apex.common.security.annotation.RequiresPermissions;
import com.apex.system.domain.dto.UserDTO;
import com.apex.system.domain.vo.UserVO;
import com.apex.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理控制器
 *
 * @author apex
 */
@Tag(name = "用户管理", description = "用户的增删改查")
@RestController
@RequestMapping("/system/user")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService userService;

    /**
     * 获取用户列表
     */
    @Operation(summary = "获取用户列表")
    @RequiresPermissions("system:user:list")
    @GetMapping("/list")
    public R<PageResult<UserVO>> list(UserDTO userDTO) {
        PageResult<UserVO> result = userService.selectUserList(userDTO);
        return R.ok(result);
    }

    /**
     * 获取用户详情
     */
    @Operation(summary = "获取用户详情")
    @RequiresPermissions("system:user:query")
    @GetMapping("/{userId}")
    public R<UserVO> getInfo(@PathVariable Long userId) {
        UserVO user = userService.selectUserById(userId);
        return R.ok(user);
    }

    /**
     * 新增用户
     */
    @Operation(summary = "新增用户")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:user:add")
    @PostMapping
    public R<Long> add(@Valid @RequestBody UserDTO userDTO) {
        Long userId = userService.insertUser(userDTO);
        return R.ok(userId);
    }

    /**
     * 修改用户
     */
    @Operation(summary = "修改用户")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:edit")
    @PutMapping
    public R<Void> edit(@Valid @RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return R.ok();
    }

    /**
     * 删除用户
     */
    @Operation(summary = "删除用户")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:user:remove")
    @DeleteMapping("/{userIds}")
    public R<Void> remove(@PathVariable List<Long> userIds) {
        userService.deleteUserByIds(userIds);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @Operation(summary = "重置密码")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:resetPwd")
    @PutMapping("/resetPwd")
    public R<Void> resetPwd(@RequestParam Long userId, @RequestParam String password) {
        userService.resetUserPwd(userId, password);
        return R.ok();
    }

    /**
     * 修改状态
     */
    @Operation(summary = "修改状态")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:edit")
    @PutMapping("/changeStatus")
    public R<Void> changeStatus(@RequestParam Long userId, @RequestParam Integer status) {
        userService.updateUserStatus(userId, status);
        return R.ok();
    }

    /**
     * 校验用户名是否唯一
     */
    @Operation(summary = "校验用户名是否唯一")
    @GetMapping("/checkUsernameUnique")
    public R<Boolean> checkUsernameUnique(@RequestParam String username) {
        boolean unique = userService.checkUsernameUnique(username);
        return R.ok(unique);
    }

    /**
     * 校验手机号是否唯一
     */
    @Operation(summary = "校验手机号是否唯一")
    @GetMapping("/checkPhoneUnique")
    public R<Boolean> checkPhoneUnique(@RequestParam String phone) {
        boolean unique = userService.checkPhoneUnique(phone);
        return R.ok(unique);
    }

    /**
     * 校验邮箱是否唯一
     */
    @Operation(summary = "校验邮箱是否唯一")
    @GetMapping("/checkEmailUnique")
    public R<Boolean> checkEmailUnique(@RequestParam String email) {
        boolean unique = userService.checkEmailUnique(email);
        return R.ok(unique);
    }
}

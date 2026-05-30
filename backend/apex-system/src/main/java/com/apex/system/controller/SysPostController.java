package com.apex.system.controller;

import com.apex.common.core.domain.PageResult;
import com.apex.common.core.domain.R;
import com.apex.common.log.annotation.Log;
import com.apex.common.log.enums.BusinessType;
import com.apex.common.security.annotation.RequiresPermissions;
import com.apex.system.domain.SysPost;
import com.apex.system.service.SysPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 岗位管理控制器
 *
 * @author apex
 */
@Tag(name = "岗位管理", description = "岗位的增删改查")
@RestController
@RequestMapping("/system/post")
@RequiredArgsConstructor
public class SysPostController {

    private final SysPostService postService;

    /**
     * 获取岗位列表
     */
    @Operation(summary = "获取岗位列表")
    @RequiresPermissions("system:post:list")
    @GetMapping("/list")
    public R<PageResult<SysPost>> list(SysPost post,
                                        @RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult<SysPost> result = postService.selectPostList(post, pageNum, pageSize);
        return R.ok(result);
    }

    /**
     * 获取岗位详情
     */
    @Operation(summary = "获取岗位详情")
    @RequiresPermissions("system:post:query")
    @GetMapping("/{postId}")
    public R<SysPost> getInfo(@PathVariable Long postId) {
        SysPost post = postService.selectPostById(postId);
        return R.ok(post);
    }

    /**
     * 新增岗位
     */
    @Operation(summary = "新增岗位")
    @Log(title = "岗位管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:post:add")
    @PostMapping
    public R<Long> add(@Valid @RequestBody SysPost post) {
        Long postId = postService.insertPost(post);
        return R.ok(postId);
    }

    /**
     * 修改岗位
     */
    @Operation(summary = "修改岗位")
    @Log(title = "岗位管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:post:edit")
    @PutMapping
    public R<Void> edit(@Valid @RequestBody SysPost post) {
        postService.updatePost(post);
        return R.ok();
    }

    /**
     * 删除岗位
     */
    @Operation(summary = "删除岗位")
    @Log(title = "岗位管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:post:remove")
    @DeleteMapping("/{postIds}")
    public R<Void> remove(@PathVariable List<Long> postIds) {
        postService.deletePostByIds(postIds);
        return R.ok();
    }

    /**
     * 校验岗位编码是否唯一
     */
    @Operation(summary = "校验岗位编码是否唯一")
    @GetMapping("/checkPostCodeUnique")
    public R<Boolean> checkPostCodeUnique(@RequestParam String postCode) {
        boolean unique = postService.checkPostCodeUnique(postCode);
        return R.ok(unique);
    }

    /**
     * 校验岗位名称是否唯一
     */
    @Operation(summary = "校验岗位名称是否唯一")
    @GetMapping("/checkPostNameUnique")
    public R<Boolean> checkPostNameUnique(@RequestParam String postName) {
        boolean unique = postService.checkPostNameUnique(postName);
        return R.ok(unique);
    }
}

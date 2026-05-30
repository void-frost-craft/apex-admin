package com.apex.job.controller;

import com.apex.common.core.domain.R;
import com.apex.common.security.annotation.RequiresPermissions;
import com.apex.job.domain.SysJob;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "定时任务")
@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class SysJobController {

    @Operation(summary = "任务列表")
    @RequiresPermissions("monitor:job:list")
    @GetMapping("/list")
    public R<Void> list() {
        return R.ok();
    }

    @Operation(summary = "新增任务")
    @RequiresPermissions("monitor:job:add")
    @PostMapping
    public R<Void> add(@RequestBody SysJob job) {
        return R.ok();
    }

    @Operation(summary = "修改任务")
    @RequiresPermissions("monitor:job:edit")
    @PutMapping
    public R<Void> edit(@RequestBody SysJob job) {
        return R.ok();
    }

    @Operation(summary = "删除任务")
    @RequiresPermissions("monitor:job:remove")
    @DeleteMapping("/{jobIds}")
    public R<Void> remove(@PathVariable Long[] jobIds) {
        return R.ok();
    }
}

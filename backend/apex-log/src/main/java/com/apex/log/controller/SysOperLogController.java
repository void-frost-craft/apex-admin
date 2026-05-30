package com.apex.log.controller;

import com.apex.common.core.domain.PageResult;
import com.apex.common.core.domain.R;
import com.apex.common.security.annotation.RequiresPermissions;
import com.apex.log.domain.SysOperLog;
import com.apex.log.service.SysOperLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "操作日志")
@RestController
@RequestMapping("/operlog")
@RequiredArgsConstructor
public class SysOperLogController {

    private final SysOperLogService operLogService;

    @Operation(summary = "操作日志列表")
    @RequiresPermissions("monitor:operlog:list")
    @GetMapping("/list")
    public R<PageResult<SysOperLog>> list(SysOperLog operLog,
                                           @RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "10") Integer pageSize) {
        return R.ok(operLogService.selectOperLogList(operLog, pageNum, pageSize));
    }

    @Operation(summary = "删除操作日志")
    @RequiresPermissions("monitor:operlog:remove")
    @DeleteMapping("/{operIds}")
    public R<Void> remove(@PathVariable Long[] operIds) {
        return R.ok(operLogService.deleteOperLogByIds(operIds) ? null : null);
    }

    @Operation(summary = "清空操作日志")
    @RequiresPermissions("monitor:operlog:remove")
    @DeleteMapping("/clean")
    public R<Void> clean() {
        return R.ok(operLogService.cleanOperLog() ? null : null);
    }
}

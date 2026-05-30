package com.apex.log.controller;

import com.apex.common.core.domain.PageResult;
import com.apex.common.core.domain.R;
import com.apex.common.security.annotation.RequiresPermissions;
import com.apex.log.domain.SysLogininfor;
import com.apex.log.service.SysLogininforService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "登录日志")
@RestController
@RequestMapping("/logininfor")
@RequiredArgsConstructor
public class SysLogininforController {

    private final SysLogininforService logininforService;

    @Operation(summary = "登录日志列表")
    @RequiresPermissions("monitor:logininfor:list")
    @GetMapping("/list")
    public R<PageResult<SysLogininfor>> list(SysLogininfor logininfor,
                                              @RequestParam(defaultValue = "1") Integer pageNum,
                                              @RequestParam(defaultValue = "10") Integer pageSize) {
        return R.ok(logininforService.selectLogininforList(logininfor, pageNum, pageSize));
    }

    @Operation(summary = "删除登录日志")
    @RequiresPermissions("monitor:logininfor:remove")
    @DeleteMapping("/{infoIds}")
    public R<Void> remove(@PathVariable Long[] infoIds) {
        return R.ok(logininforService.deleteLogininforByIds(infoIds) ? null : null);
    }

    @Operation(summary = "清空登录日志")
    @RequiresPermissions("monitor:logininfor:remove")
    @DeleteMapping("/clean")
    public R<Void> clean() {
        return R.ok(logininforService.cleanLogininfor() ? null : null);
    }
}

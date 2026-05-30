package com.apex.message.controller;

import com.apex.common.core.domain.R;
import com.apex.common.security.annotation.RequiresPermissions;
import com.apex.message.domain.SysMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "消息管理")
@RestController
@RequestMapping("/message")
public class SysMessageController {

    @Operation(summary = "消息列表")
    @RequiresPermissions("system:message:list")
    @GetMapping("/list")
    public R<Void> list() {
        return R.ok();
    }

    @Operation(summary = "发送消息")
    @RequiresPermissions("system:message:add")
    @PostMapping
    public R<Void> send(@RequestBody SysMessage message) {
        return R.ok();
    }

    @Operation(summary = "删除消息")
    @RequiresPermissions("system:message:remove")
    @DeleteMapping("/{noticeIds}")
    public R<Void> remove(@PathVariable Long[] noticeIds) {
        return R.ok();
    }
}

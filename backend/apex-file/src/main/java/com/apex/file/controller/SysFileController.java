package com.apex.file.controller;

import com.apex.common.core.domain.R;
import com.apex.common.security.annotation.RequiresPermissions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "文件管理")
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class SysFileController {

    @Operation(summary = "上传文件")
    @RequiresPermissions("system:file:add")
    @PostMapping("/upload")
    public R<Void> upload(@RequestParam("file") MultipartFile file) {
        return R.ok();
    }

    @Operation(summary = "下载文件")
    @RequiresPermissions("system:file:query")
    @GetMapping("/download/{fileId}")
    public R<Void> download(@PathVariable Long fileId) {
        return R.ok();
    }

    @Operation(summary = "删除文件")
    @RequiresPermissions("system:file:remove")
    @DeleteMapping("/{fileIds}")
    public R<Void> remove(@PathVariable Long[] fileIds) {
        return R.ok();
    }
}

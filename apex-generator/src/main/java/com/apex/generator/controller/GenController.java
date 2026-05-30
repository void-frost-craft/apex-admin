package com.apex.generator.controller;

import com.apex.common.core.domain.R;
import com.apex.common.security.annotation.RequiresPermissions;
import com.apex.generator.domain.GenTable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "代码生成")
@RestController
@RequestMapping("/gen")
public class GenController {

    @Operation(summary = "表列表")
    @RequiresPermissions("tool:gen:list")
    @GetMapping("/table/list")
    public R<List<GenTable>> list() {
        return R.ok();
    }

    @Operation(summary = "导入表")
    @RequiresPermissions("tool:gen:import")
    @PostMapping("/importTable")
    public R<Void> importTable(@RequestBody List<String> tableNames) {
        return R.ok();
    }

    @Operation(summary = "生成代码")
    @RequiresPermissions("tool:gen:code")
    @GetMapping("/genCode/{tableName}")
    public R<Void> genCode(@PathVariable String tableName) {
        return R.ok();
    }

    @Operation(summary = "预览代码")
    @RequiresPermissions("tool:gen:preview")
    @GetMapping("/preview/{tableId}")
    public R<Void> preview(@PathVariable Long tableId) {
        return R.ok();
    }
}

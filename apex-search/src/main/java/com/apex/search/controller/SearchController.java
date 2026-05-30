package com.apex.search.controller;

import com.apex.common.core.domain.R;
import com.apex.common.security.annotation.RequiresPermissions;
import com.apex.search.domain.SearchDocument;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "搜索管理")
@RestController
@RequestMapping("/search")
public class SearchController {

    @Operation(summary = "全文搜索")
    @RequiresPermissions("search:query")
    @GetMapping
    public R<List<SearchDocument>> search(@RequestParam String keyword) {
        return R.ok();
    }

    @Operation(summary = "索引文档")
    @RequiresPermissions("search:add")
    @PostMapping("/index")
    public R<Void> index(@RequestBody SearchDocument document) {
        return R.ok();
    }

    @Operation(summary = "删除索引")
    @RequiresPermissions("search:remove")
    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable String id) {
        return R.ok();
    }
}

package com.apex.monitor.controller;

import com.apex.common.core.domain.R;
import com.apex.monitor.domain.Server;
import com.apex.monitor.utils.ServerUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "服务器监控")
@RestController
@RequestMapping("/server")
public class ServerController {

    @Operation(summary = "获取服务器信息")
    @GetMapping
    public R<Server> getInfo() {
        Server server = ServerUtils.getServerInfo();
        return R.ok(server);
    }
}

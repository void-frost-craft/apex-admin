package com.apex.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 网关配置
 *
 * @author apex
 */
@Configuration
public class GatewayConfig {

    /**
     * 自定义路由配置
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // 认证服务路由
                .route("apex-auth", r -> r.path("/auth/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://apex-auth"))
                // 系统管理服务路由
                .route("apex-system", r -> r.path("/system/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://apex-system"))
                // 日志服务路由
                .route("apex-log", r -> r.path("/log/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://apex-log"))
                // 定时任务服务路由
                .route("apex-job", r -> r.path("/job/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://apex-job"))
                // 文件服务路由
                .route("apex-file", r -> r.path("/file/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://apex-file"))
                // 消息服务路由
                .route("apex-message", r -> r.path("/message/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://apex-message"))
                // 搜索服务路由
                .route("apex-search", r -> r.path("/search/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://apex-search"))
                // 监控服务路由
                .route("apex-monitor", r -> r.path("/monitor/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://apex-monitor"))
                // 代码生成服务路由
                .route("apex-generator", r -> r.path("/generator/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://apex-generator"))
                .build();
    }
}

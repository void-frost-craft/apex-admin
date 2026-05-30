package com.apex.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 请求过滤器
 *
 * @author apex
 */
@Slf4j
@Component
public class RequestFilter implements GlobalFilter, Ordered {

    private static final String START_TIME = "startTime";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // 记录请求开始时间
        exchange.getAttributes().put(START_TIME, System.currentTimeMillis());

        // 获取请求信息
        String path = request.getPath().value();
        String method = request.getMethod().name();
        String ip = request.getRemoteAddress() != null ?
                request.getRemoteAddress().getAddress().getHostAddress() : "unknown";

        log.info("请求开始: {} {} {}", method, path, ip);

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // 记录请求结束时间
            Long startTime = exchange.getAttribute(START_TIME);
            if (startTime != null) {
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;

                log.info("请求结束: {} {} 耗时: {}ms", method, path, duration);

                // 记录慢请求
                if (duration > 3000) {
                    log.warn("慢请求警告: {} {} 耗时: {}ms", method, path, duration);
                }
            }
        }));
    }

    @Override
    public int getOrder() {
        return -100;
    }
}

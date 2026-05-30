package com.apex.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * Sentinel 配置
 *
 * @author apex
 */
@Configuration
public class SentinelConfig {

    private final List<ViewResolver> viewResolvers;
    private final org.springframework.web.reactive.result.view.ViewResolver viewResolver;

    public SentinelConfig(List<ViewResolver> viewResolvers,
                          org.springframework.web.reactive.result.view.ViewResolver viewResolver) {
        this.viewResolvers = viewResolvers;
        this.viewResolver = viewResolver;
    }

    /**
     * Sentinel 限流异常处理
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, viewResolver);
    }

    /**
     * Sentinel 过滤器
     */
    @Bean
    @Order(-1)
    public SentinelGatewayFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }
}

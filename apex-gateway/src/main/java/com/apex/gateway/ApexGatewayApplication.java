package com.apex.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * API 网关服务启动类
 *
 * @author apex
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApexGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApexGatewayApplication.class, args);
        System.out.println("========== Apex Gateway 启动成功 ==========");
    }
}

package com.apex.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 认证授权服务启动类
 *
 * @author apex
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.apex.api")
public class ApexAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApexAuthApplication.class, args);
        System.out.println("========== Apex Auth 启动成功 ==========");
    }
}

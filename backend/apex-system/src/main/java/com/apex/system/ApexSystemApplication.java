package com.apex.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 系统管理服务启动类
 *
 * @author apex
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.apex.api")
public class ApexSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApexSystemApplication.class, args);
        System.out.println("========== Apex System 启动成功 ==========");
    }
}

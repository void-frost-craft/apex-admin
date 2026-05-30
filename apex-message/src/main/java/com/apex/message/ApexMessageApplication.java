package com.apex.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 消息服务启动类
 *
 * @author apex
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.apex.api")
public class ApexMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApexMessageApplication.class, args);
        System.out.println("========== Apex Message 启动成功 ==========");
    }
}

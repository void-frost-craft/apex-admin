package com.apex.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 代码生成服务启动类
 *
 * @author apex
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.apex.api")
public class ApexGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApexGeneratorApplication.class, args);
        System.out.println("========== Apex Generator 启动成功 ==========");
    }
}

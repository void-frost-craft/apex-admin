package com.apex.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 定时任务服务启动类
 *
 * @author apex
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.apex.api")
public class ApexJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApexJobApplication.class, args);
        System.out.println("========== Apex Job 启动成功 ==========");
    }
}

package com.apex.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 搜索服务启动类
 *
 * @author apex
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.apex.api")
public class ApexSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApexSearchApplication.class, args);
        System.out.println("========== Apex Search 启动成功 ==========");
    }
}

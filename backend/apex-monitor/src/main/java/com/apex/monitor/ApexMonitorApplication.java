package com.apex.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 监控服务启动类
 *
 * @author apex
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.apex.api")
public class ApexMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApexMonitorApplication.class, args);
        System.out.println("========== Apex Monitor 启动成功 ==========");
    }
}

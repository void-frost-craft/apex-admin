package com.apex.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 文件服务启动类
 *
 * @author apex
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.apex.api")
public class ApexFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApexFileApplication.class, args);
        System.out.println("========== Apex File 启动成功 ==========");
    }
}

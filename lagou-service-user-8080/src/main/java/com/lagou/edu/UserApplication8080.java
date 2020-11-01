package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan("com.lagou.edu.pojo")
@EnableDiscoveryClient
@EnableFeignClients  // 开启Feign 客户端功能
public class UserApplication8080 {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication8080.class, args);
    }

}



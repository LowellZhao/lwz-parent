package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients  // 开启Feign 客户端功能
public class GateWayApplication9002 {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication9002.class,args);
    }
}


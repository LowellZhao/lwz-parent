package com.lagou.edu.cloud.service;

import com.lagou.edu.cloud.fallback.EmailServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient表明当前类是一个Feign客户端，value指定该客户端要请求的服务名称（登记到注册中心上的服务提供者的服务名称）
@FeignClient(value = "lagou-service-email", fallback = EmailServiceFallback.class, path = "/email")
public interface EmailServiceFeignClient {

    @GetMapping("/{email}/{code}")
    Boolean send(@PathVariable("email") String email, @PathVariable("code") String code);

}

package com.lagou.edu.cloud.service;

import com.lagou.edu.cloud.fallback.CodeServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient表明当前类是一个Feign客户端，value指定该客户端要请求的服务名称（登记到注册中心上的服务提供者的服务名称）
@FeignClient(value = "lagou-service-code", fallback = CodeServiceFallback.class, path = "/code")
public interface CodeServiceFeignClient {

    @GetMapping("/validate/{email}/{code}")
    Integer validate(@PathVariable("email") String email, @PathVariable("code") String code);

}

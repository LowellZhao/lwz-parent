package com.lagou.edu.cloud.service;

import com.lagou.edu.cloud.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// @FeignClient表明当前类是一个Feign客户端，value指定该客户端要请求的服务名称（登记到注册中心上的服务提供者的服务名称）
@FeignClient(value = "lagou-service-user", fallback = UserServiceFallback.class, path = "/user")
public interface UserServiceFeignClient {

    @GetMapping("/info/{token}")
    String info(@PathVariable("token") String token);

}

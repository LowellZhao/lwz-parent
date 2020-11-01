package com.lagou.edu.cloud.fallback;

import com.lagou.edu.cloud.service.UserServiceFeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class UserServiceFallback implements UserServiceFeignClient {

    @Override
    public String info(@PathVariable("token") String token) {
        return null;
    }

}

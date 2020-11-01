package com.lagou.edu.cloud.fallback;

import com.lagou.edu.cloud.service.EmailServiceFeignClient;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceFallback implements EmailServiceFeignClient {

    @Override
    public Boolean send(String email, String code) {
        return false;
    }
}

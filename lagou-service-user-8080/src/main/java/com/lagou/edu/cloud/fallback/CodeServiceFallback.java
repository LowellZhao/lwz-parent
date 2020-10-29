package com.lagou.edu.cloud.fallback;

import com.lagou.edu.cloud.service.CodeServiceFeignClient;
import org.springframework.stereotype.Component;

@Component
public class CodeServiceFallback implements CodeServiceFeignClient {

    @Override
    public Integer validate(String email, String code) {
        return 1;
    }

}

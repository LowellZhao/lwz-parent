package com.lagou.edu.service.impl;

import com.lagou.edu.cloud.service.EmailServiceFeignClient;
import com.lagou.edu.dao.AuthCodeDao;
import com.lagou.edu.pojo.LagouAuthCode;
import com.lagou.edu.service.CodeService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class CodeServiceImpl implements CodeService {

    @Resource
    private AuthCodeDao authCodeDao;

    @Resource
    private EmailServiceFeignClient emailServiceFeignClient;

    @Override
    public Integer validate(String email, String code) {
        LagouAuthCode authCode = new LagouAuthCode();
        authCode.setEmail(email).setCode(code);

        Optional<LagouAuthCode> one = authCodeDao.findOne(Example.of(authCode));
        if (one.isPresent()) {
            LagouAuthCode authCode1 = one.get();
            LocalDateTime expireTime = authCode1.getExpiretime();
            LocalDateTime now = LocalDateTime.now();
            if (expireTime.isBefore(now)) {
                return 2;
            }
            return 0;
        }
        return 1;
    }

    @Override
    public Boolean create(String email) {
        int code = new Random().nextInt(899999) + 100000;
        LagouAuthCode lagouAuthCode = new LagouAuthCode();
        LocalDateTime now = LocalDateTime.now();
        lagouAuthCode.setCode(code + "").setEmail(email).setCreatetime(now).setExpiretime(now.plusMinutes(5L));
        LagouAuthCode save = authCodeDao.save(lagouAuthCode);

        if (save.getId() != null) {
            Boolean send = emailServiceFeignClient.send(email, code + "");
            return send;
        }

        return false;
    }

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println(now.plusMinutes(5));
    }

}

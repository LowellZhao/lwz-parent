package com.lagou.edu.service.impl;

import com.lagou.edu.dao.TokenDao;
import com.lagou.edu.pojo.LagouToken;
import com.lagou.edu.service.UserService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private TokenDao tokenDao;

    @Override
    public LagouToken register(String email, String password, String code) {
        String token = UUID.nameUUIDFromBytes((password).getBytes()).toString();
        LagouToken lagouToken = new LagouToken();
        lagouToken.setEmail(email).setToken(token);
        return tokenDao.save(lagouToken);
    }

    @Override
    public Boolean isRegistered(String email) {
        LagouToken lagouToken = new LagouToken();
        lagouToken.setEmail(email);
        return tokenDao.findOne(Example.of(lagouToken)).isPresent();
    }

    @Override
    public LagouToken login(String email, String password) {
        String token = UUID.nameUUIDFromBytes((password).getBytes()).toString();
        LagouToken lagouToken = new LagouToken();
        lagouToken.setEmail(email).setToken(token);
        Optional<LagouToken> one = tokenDao.findOne(Example.of(lagouToken));
        return one.orElse(null);
    }

    @Override
    public String info(String token) {
        LagouToken lagouToken = new LagouToken();
        lagouToken.setToken(token);
        Optional<LagouToken> one = tokenDao.findOne(Example.of(lagouToken));
        return one.map(LagouToken::getEmail).orElse(null);
    }

    public static void main(String[] args) {
        UUID uuid = UUID.nameUUIDFromBytes(("123456").getBytes());
        System.out.println(uuid);
        UUID uuid2 = UUID.nameUUIDFromBytes(("123456").getBytes());
        System.out.println(uuid2);
    }
}

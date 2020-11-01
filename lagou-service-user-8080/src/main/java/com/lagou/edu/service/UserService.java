package com.lagou.edu.service;

import com.lagou.edu.pojo.LagouToken;

public interface UserService {

    LagouToken register(String email, String password, String code);

    Boolean isRegistered(String email);

    LagouToken login(String email, String password);

    String info(String token);

}

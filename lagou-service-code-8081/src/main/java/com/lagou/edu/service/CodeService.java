package com.lagou.edu.service;

public interface CodeService {

    Integer validate(String email, String code);

    Boolean create(String email);

}

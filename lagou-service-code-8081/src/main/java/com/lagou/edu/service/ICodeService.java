package com.lagou.edu.service;

public interface ICodeService {

    Integer validate(String email, String code);

    Boolean create(String email);

}

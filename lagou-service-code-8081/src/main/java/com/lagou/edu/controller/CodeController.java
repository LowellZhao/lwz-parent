package com.lagou.edu.controller;

import com.lagou.edu.service.CodeService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/code")
public class CodeController {

    @Resource
    private CodeService codeService;

    @RequestMapping("/create/{email}")
    public Boolean create(@PathVariable("email") String email) {
        Boolean aBoolean = codeService.create(email);
        return aBoolean;
    }

    @RequestMapping("/validate/{email}/{code}")
    public Integer validate(@PathVariable("email") String email,
                            @PathVariable("code") String code) {
        return codeService.validate(email, code);
    }

}

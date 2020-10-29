package com.lagou.edu.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/code")
public class CodeController {

    @RequestMapping("/create/{email}")
    public Boolean create(@PathVariable("email") String email) {
        return false;
    }

    @RequestMapping("/validate/{email}/{code}")
    public Integer validate(@PathVariable("email") String email,
                            @PathVariable("code") String code) {
        return 1;
    }

}

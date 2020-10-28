package com.lagou.edu.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/register/{email}/{password}/{code}")
    public Boolean register(@PathVariable("email") String email,
                            @PathVariable("password") String password,
                            @PathVariable("code") String code) {
        return false;
    }

    @RequestMapping("/isRegistered/{email}")
    public Boolean isRegistered(@PathVariable("email") String email) {
        return false;
    }

    @RequestMapping("/login/{email}/{password}")
    public String login(@PathVariable("email") String email,
                        @PathVariable("password") String password) {
        return null;
    }

    @RequestMapping("/login/{token}")
    public String info(@PathVariable("token") String email) {
        return null;
    }
}

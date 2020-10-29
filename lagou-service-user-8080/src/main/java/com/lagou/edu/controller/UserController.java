package com.lagou.edu.controller;

import com.lagou.edu.cloud.service.CodeServiceFeignClient;
import com.lagou.edu.pojo.LagouToken;
import com.lagou.edu.service.IUserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @Resource
    private CodeServiceFeignClient codeServiceFeignClient;


    @RequestMapping("/register/{email}/{password}/{code}")
    public Boolean register(@PathVariable("email") String email,
                            @PathVariable("password") String password,
                            @PathVariable("code") String code,
                            HttpServletRequest request,
                            HttpServletResponse response) {


        Integer validate = codeServiceFeignClient.validate(email, code);

        LagouToken token = userService.register(email, password, code);
        Cookie cookie = new Cookie("token", token.getToken());
        cookie.setMaxAge(300);
        response.addCookie(cookie);

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

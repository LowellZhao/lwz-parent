package com.lagou.edu.controller;

import com.lagou.edu.cloud.service.CodeServiceFeignClient;
import com.lagou.edu.pojo.LagouToken;
import com.lagou.edu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private CodeServiceFeignClient codeServiceFeignClient;

    @RequestMapping("/register/{email}/{password}/{code}")
    public Integer register(@PathVariable("email") String email,
                            @PathVariable("password") String password,
                            @PathVariable("code") String code,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        Integer validate = codeServiceFeignClient.validate(email, code);
        if (!Integer.valueOf(0).equals(validate)) {
            return validate;
        }

        LagouToken token = userService.register(email, password, code);
        if (token.getId() == null) {
            return 1;
        }
        Cookie cookie = new Cookie("token", token.getToken());
        cookie.setPath("/");
        response.addCookie(cookie);

        return 0;
    }

    @RequestMapping("/isRegistered/{email}")
    public Boolean isRegistered(@PathVariable("email") String email) {
        return userService.isRegistered(email);
    }

    @RequestMapping("/login/{email}/{password}")
    public String login(@PathVariable("email") String email,
                        @PathVariable("password") String password,
                        HttpServletResponse response) {
        LagouToken login = userService.login(email, password);
        if (login == null) {
            return null;
        }

        Cookie cookie = new Cookie("token", login.getToken());
        cookie.setPath("/");
        response.addCookie(cookie);

        return login.getEmail();
    }

    @RequestMapping("/info/{token}")
    public String info(@PathVariable("token") String token) {
        return userService.info(token);
    }
}

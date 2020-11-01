package com.lagou.edu.controller;

import com.lagou.edu.service.EmailService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Resource
    private EmailService emailService;

    @RequestMapping("/{email}/{code}")
    public Boolean send(@PathVariable("email") String email,
                        @PathVariable("code") String code) {
        return emailService.sendSimpleMailMessge(email, code);
    }

}

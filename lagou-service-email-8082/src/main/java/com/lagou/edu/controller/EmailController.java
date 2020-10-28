package com.lagou.edu.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @RequestMapping("/{email}/{code}")
    public Boolean send(@PathVariable("email") String email,
                        @PathVariable("code") String code) {
        return false;
    }

}

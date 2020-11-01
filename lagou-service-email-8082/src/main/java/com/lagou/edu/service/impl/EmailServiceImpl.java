package com.lagou.edu.service.impl;

import com.lagou.edu.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmailServiceImpl implements EmailService {

    @Resource
    private JavaMailSender mailSender;

    /**
     * 发送普通邮件
     *
     * @param to      收件人
     * @param content 内容
     */
    @Override
    public Boolean sendSimpleMailMessge(String to, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("15223538324@163.com");
        message.setTo(to);
        message.setSubject("验证码");
        message.setText(content);
        try {
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}

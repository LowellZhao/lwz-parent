package com.lagou.edu.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class LagouAuthCode {

    private Integer id;

    private String email;

    private String code;

    private LocalDateTime createtime;

    private LocalDateTime expiretime;

}

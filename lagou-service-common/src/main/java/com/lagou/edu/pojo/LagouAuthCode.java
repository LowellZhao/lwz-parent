package com.lagou.edu.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "lagou_auth_code")
public class LagouAuthCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String code;

    private LocalDateTime createtime;

    private LocalDateTime expiretime;

}

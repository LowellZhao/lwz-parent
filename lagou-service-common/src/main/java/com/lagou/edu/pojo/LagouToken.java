package com.lagou.edu.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LagouToken {

    private Integer id;

    private String email;

    private String token;

}

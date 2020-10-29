package com.lagou.edu.dao;

import com.lagou.edu.pojo.LagouAuthCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthCodeDao extends JpaRepository<LagouAuthCode, Integer> {

}

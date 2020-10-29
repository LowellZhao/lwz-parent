package com.lagou.edu.dao;

import com.lagou.edu.pojo.LagouToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenDao extends JpaRepository<LagouToken, Integer> {
}

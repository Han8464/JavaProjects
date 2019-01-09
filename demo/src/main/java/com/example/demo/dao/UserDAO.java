package com.example.demo.dao;

import com.example.demo.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository <User, Long> {

    User findByUsername(String username);
}

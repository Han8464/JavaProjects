package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLOutput;

@Controller
public class LoginController {

    private final UserDAO userDAO;

    @Autowired
    public LoginController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping(value = "/loginResult", method = RequestMethod.POST)
    public String login( String username, String password) {
        User user = userDAO.findByUsername(username);
        if(user != null && password.equals(user.getPassword())){
            return "redirect:/create_qn.html";
        }
        return "redirect:/login.html";
    }
}


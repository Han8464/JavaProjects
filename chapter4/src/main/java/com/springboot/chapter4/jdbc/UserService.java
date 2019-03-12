package com.springboot.chapter4.jdbc;

import com.springboot.chapter4.pojo.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserService {
    public int insertUser() {
        UserDao userDao = new UserDao();
        User user = new User();
        user.setUsername("user_name_1");
        user.setNote("note_1");
        Connection conn = null;
        int result = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chapter4", "root", "root");
            conn.setAutoCommit(false);
            result = userDao.insertUser(conn, user);
        }catch (Exception e) {
            try {
                conn.rollback();
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            if(conn != null) {
                try {
                    conn.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return  result;
    }
}

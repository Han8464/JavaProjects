package com.springboot.chapter3.config;
import com.springboot.chapter3.condition.DatabaseConditional;
import com.springboot.chapter3.pojo.User;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.springboot.chapter3.*", lazyInit = true)
public class AppConfig{
    @Bean(name = "user2")
    public User initUser(){
        User user = new User();
        user.setId(1L);
        user.setUserName("user_name_1");
        user.setNote("note_1");
        return user;
    }
    @Bean(name="dataSource", destroyMethod = "close")
    @Conditional(DatabaseConditional.class)
    public DataSource getDataSource(
            @Value("${database.driverName}") String driver,
            @Value("${database.url}") String url,
            @Value("${database.username}") String username,
            @Value("${database.password}") String password
    ) {
        Properties props = new Properties();
        props.setProperty("driver", "com.mysql.jdbc.Driver");
        props.setProperty("url", "jdbc:mysql://localhost:3306/chapter3");
        props.setProperty("username", "root");
        props.setProperty("password", "123456");
        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(props);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

}


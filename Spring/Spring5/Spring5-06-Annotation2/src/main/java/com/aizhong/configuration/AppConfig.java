package com.aizhong.configuration;

import com.aizhong.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class AppConfig {

    /*
        创建简单对象
     */
    @Bean
    public User user1(){
        User user = new User();
        user.setName("app1");
        System.out.println("AppConfig.user");
        return user;
    }

    /*
        创建复杂对象
     */
    @Bean
    @Scope("singleton")
    public Connection conn123(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?useSSL=false","root","root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}

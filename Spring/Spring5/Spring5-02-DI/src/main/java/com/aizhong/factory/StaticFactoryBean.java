package com.aizhong.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StaticFactoryBean {
    // 静态方法
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web?useSSL=false", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}

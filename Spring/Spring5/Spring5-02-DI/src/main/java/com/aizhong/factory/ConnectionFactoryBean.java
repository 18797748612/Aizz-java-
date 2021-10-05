package com.aizhong.factory;

import org.springframework.beans.factory.FactoryBean;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactoryBean implements FactoryBean<Connection> {
    private String driveName;
    private String URL;
    private String user;
    private String password;

    public String getDriveName() {
        return driveName;
    }

    public void setDriveName(String driveName) {
        this.driveName = driveName;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 用于创建复杂对象
    @Override
    public Connection getObject() throws Exception {
        Class.forName(driveName);
        return DriverManager.getConnection(URL, user, password);
    }

    // 返回对象的类型
    @Override
    public Class<?> getObjectType() {
        return Connection.class;
    }

    // 是否单例
    // 在接口中有默认实现，默认是单例
    @Override
    public boolean isSingleton() {
        return false;
    }
}

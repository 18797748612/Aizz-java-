package com.aizhong.configuration;

import com.aizhong.dao.UserDao;
import com.aizhong.dao.UserDaoImpl;
import com.aizhong.service.UserService;
import com.aizhong.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig2 {

    @Bean
    public UserDao userDao(){
        System.out.println("AppConfig2.userDao");
        return new UserDaoImpl();
    }

    /*
        通过参数赋值，会有个自动注入的过程
     */
    @Bean
    public UserService userService(UserDao userDao){
        return new UserServiceImpl(userDao);
    }

    /*
        通过方法赋值，并没有再次调用方法
     */
    @Bean
    public UserService userService(){
        return new UserServiceImpl(userDao());
    }

}

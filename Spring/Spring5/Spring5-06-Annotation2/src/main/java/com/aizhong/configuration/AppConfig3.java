package com.aizhong.configuration;

import com.aizhong.dao.UserDaoImpl;
import com.aizhong.entity.User;
import com.aizhong.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:/init.properties")
//@ComponentScan(basePackages = "com.aizhong.configuration")
//@Import({UserDaoImpl.class})
public class AppConfig3 {

    @Value("${name}")
    private String name = null;

    @Value("${age}")
    private Integer age = 0;

    @Bean
    public User user(){
        System.out.println("AppConfig3.user");
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

}

package com.aizhong.service.proxy;

import com.aizhong.entity.User;
import com.aizhong.service.UserService;
import com.aizhong.service.impl.UserServiceImpl;

/**
 *  静态代理类
 */
public class UserServiceProxy implements UserService {
    private UserService userService = new UserServiceImpl();
    @Override
    public void register(User user) {
        System.out.println("---------------log-------------");
        userService.register(user);
    }

    @Override
    public boolean login(String name, String password) {
        System.out.println("---------------log-------------");
        return userService.login(name,password);
    }
}

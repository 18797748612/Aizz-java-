package com.aizhong.service.impl;

import com.aizhong.annotation.Log;
import com.aizhong.entity.User;
import com.aizhong.service.UserService;

/**
 *  原始类，提供原始方法
 */
public class UserServiceImpl implements UserService {
    @Override
    public void register(User user) {
        System.out.println("user = " + user);
    }

    @Log
    @Override
    public boolean login(String name, String password) {
        System.out.println("name = " + name + ", password = " + password);
        return true;
    }
}

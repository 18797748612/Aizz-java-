package com.aizhong.dao;

import com.aizhong.entity.User;

public class UserDaoImpl implements UserDao{
    @Override
    public void saveUser(User user) {
        System.out.println("user = " + user);
    }

    @Override
    public void queryUserByNameAndPassword(User user) {
        System.out.println("user = " + user);
    }
}

package com.aizhong.componentDemo;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{
    @Override
    public void save() {
        System.out.println("UserDaoImpl.save");
    }
}

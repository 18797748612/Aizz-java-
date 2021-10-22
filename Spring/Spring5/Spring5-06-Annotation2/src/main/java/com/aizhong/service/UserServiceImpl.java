package com.aizhong.service;

import com.aizhong.dao.UserDao;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
}

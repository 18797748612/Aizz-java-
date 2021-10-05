package com.aizhong.service;

import com.aizhong.dao.UserDao;
import com.aizhong.dao.UserDaoImpl;
import com.aizhong.entity.User;

public class UserServiceImpl implements UserService{
    // 耦合
    UserDao userDao = new UserDaoImpl();
    //UserDao userDao = BeanFactory.getUserDao();

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public void queryUserByNameAndPassword(User user) {
        userDao.queryUserByNameAndPassword(user);
    }
}

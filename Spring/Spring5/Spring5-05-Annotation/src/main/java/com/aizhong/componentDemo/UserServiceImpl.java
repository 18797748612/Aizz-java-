package com.aizhong.componentDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService{
//    @Autowired
//    @Resource(name="userDaoImpl")
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
//    @Autowired
//    @Resource(name="userDaoImpl")
//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }

    @Override
    public void save() {
        userDao.save();
        System.out.println("UserServiceImpl.save");
    }
}

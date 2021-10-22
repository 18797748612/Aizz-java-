package com.aizhong.service;

import com.aizhong.dao.UserDao;
import com.aizhong.entity.User;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(
        isolation = Isolation.DEFAULT,
        propagation = Propagation.REQUIRED,
        readOnly = false,
        timeout = -1,
        rollbackFor = java.lang.RuntimeException.class,
        noRollbackFor = {}

)
public class UserServiceImpl implements UserService{
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }
}

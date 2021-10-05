package com.aizhong.dao;

import com.aizhong.entity.User;

public interface UserDao {
    void saveUser(User user);
    void queryUserByNameAndPassword(User user);
}

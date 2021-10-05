package com.aizhong.service;

import com.aizhong.entity.User;

public interface UserService {
    void saveUser(User user);
    void queryUserByNameAndPassword(User user);
}

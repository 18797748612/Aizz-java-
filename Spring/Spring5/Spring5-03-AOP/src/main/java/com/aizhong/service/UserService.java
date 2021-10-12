package com.aizhong.service;

import com.aizhong.entity.User;

public interface UserService {
    void register(User user);
    boolean login(String name, String password);
}

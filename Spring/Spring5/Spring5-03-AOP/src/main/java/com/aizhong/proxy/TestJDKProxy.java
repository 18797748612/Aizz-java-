package com.aizhong.proxy;

import com.aizhong.entity.User;
import com.aizhong.service.UserService;
import com.aizhong.service.impl.UserServiceImpl;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 1. 借⽤类加载器  TestJDKProxy 或 UserServiceImpl 都可以
 * 2. JDK8.x 前必须加 final
 * final UserService userService = new UserServiceImpl();
 */
public class TestJDKProxy {

    public static void main(String[] args) {
        // 1. 创建原始对象
        UserService userService = new UserServiceImpl();

        // 2. JDK 动态代理
        InvocationHandler handler = (proxy, method, args1) -> {
            System.out.println("---- proxy log ----");
            // 原始方法运行,反射
            Object ret = method.invoke(userService, args1);
            return ret;
        };

        // 创建代理对象
        UserService proxyInstance = (UserService) Proxy.newProxyInstance(
                Thread.currentThread().getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                handler);

        proxyInstance.register(new User());

        proxyInstance.login("Aizz", "123");
    }

}

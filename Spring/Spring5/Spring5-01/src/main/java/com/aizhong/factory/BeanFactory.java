package com.aizhong.factory;

import com.aizhong.dao.UserDaoImpl;
import com.aizhong.service.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BeanFactory {
    private static Properties env = new Properties();

    static {
        try {
            //获得IO输入流
            InputStream inputStream = BeanFactory.class.getResourceAsStream("/applicationContext.properties");
            //文件内容分装
            env.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
            对象的创建方式：
                1、直接通过调用构造方法 UserService userService = new UserService();
            key 小配置文件中的key
    */
    public static Object getBean(String key) {
        Object obj = null;
        Class clazz = null;
        try {
            clazz = Class.forName(env.getProperty(key));
            obj = clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /*
        对象的创建方式：
            1、直接通过调用构造方法 UserService userService = new UserService();
            2、通过反射的形式，创建对象，解耦合
     */
    /*public static UserService getUserService() {
        //return new UserServiceImpl();
        UserService userService = null;
        try {
            Class clazz = Class.forName(env.getProperty("userService"));
            userService = (UserService) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return userService;
    }

    public static UserDaoImpl getUserDao() {
        UserDaoImpl userDaoImpl = null;
        try {
            Class clazz = Class.forName(env.getProperty("userDao"));
            userDaoImpl = (UserDaoImpl) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userDaoImpl;
    }*/

}

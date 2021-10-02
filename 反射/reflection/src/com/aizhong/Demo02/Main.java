package com.aizhong.Demo02;

import com.aizhong.pojo.Animal;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/*
    传统方法
       1、new 一个对象
       2、调用其方法
       在需要更改创建的对象或调佣其他方法时，必须要修改源码
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/com/aizhong/Demo02/re.properties"));
        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");
        System.out.println("创建 "+className+",并调用 "+methodName);

//        m0(className, methodName);
        m1();
        m2(className, methodName);
        m3(className, methodName);

    }

    private static void m0(String className, String methodName) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // 1、获取Class对象
        Class<?> aClass = Class.forName(className);
        // 2、通过class对象创建一个实例
        Object o = aClass.newInstance();
        // 3、通过class对象获取method对象
        Method method = aClass.getMethod(methodName);
        // 4、执行方法
        method.invoke(o);
    }
    public static void m1(){
        long start = System.currentTimeMillis();
        Animal animal = new Animal();
        for (int i = 0; i < 100000000; i++) {
            animal.sleep();
        }
        long end = System.currentTimeMillis();
        System.out.println("m1耗时 = "+(end-start));
    }

    public static void m2(String className, String methodName) throws Exception {
        long start = System.currentTimeMillis();
        Class<?> aClass = Class.forName(className);
        Object o = aClass.newInstance();
        Method method = aClass.getMethod(methodName);
        for (int i = 0; i < 100000000; i++) {
            method.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("m2耗时 = "+(end-start));
    }
    public static void m3(String className, String methodName) throws Exception {
        long start = System.currentTimeMillis();
        Class<?> aClass = Class.forName(className);
        Object o = aClass.newInstance();
        Method method = aClass.getMethod(methodName);
        method.setAccessible(true);
        for (int i = 0; i < 100000000; i++) {
            method.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("m3耗时 = "+(end-start));
    }
}

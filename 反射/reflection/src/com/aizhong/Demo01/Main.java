package com.aizhong.Demo01;

import com.aizhong.pojo.Animal;
import sun.reflect.Reflection;

import java.io.Serializable;

public class Main {
    public static void main(String[] args) throws Exception {
//        getClassInst3();
        test01();
    }

    // 基本数据类型也包装类的Class
    private static void test01() {

        System.out.println(int.class);// 基本数据类型
        System.out.println(Serializable.class);// 接口
        System.out.println(Thread.State.class);// 枚举
        System.out.println(Void.class);// void
        System.out.println(Deprecated.class);// 注解
        System.out.println(double[].class);// 数组
        System.out.println(Class.class);// Class类
        System.out.println(Integer.class);// 包装类
        System.out.println(String.class);// string类
    }

    // 获取类Class的三种方式
    private static void getClassInst3() throws ClassNotFoundException {
        // 1、Class.forName();
        Class<?> aClass = Class.forName("com.aizhong.pojo.Animal");
        System.out.println(aClass.hashCode());
        // 2、通过类的静态属性
        Class<Animal> animalClass = Animal.class;
        System.out.println(animalClass.hashCode());
        // 3、通过实例对象 getClass() 方法
        Animal animal = new Animal();
        Class<? extends Animal> aClass1 = animal.getClass();
        System.out.println(aClass1.hashCode());
    }
}

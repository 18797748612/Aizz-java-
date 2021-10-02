package com.aizhong.singleIns;

import java.lang.reflect.Constructor;

public class Main {
    public static void main(String[] args) throws Exception {
//        unsafe01();
//        unsafe02();
//        safe();
        Class<?> HSIns = Class.forName("com.aizhong.singleIns.HungrySingleIns02");
        Constructor<?> constructor = HSIns.getDeclaredConstructor();
        Object o1 = HSIns.newInstance();
        Object o2 = HSIns.newInstance();
        System.out.println(o1);
        System.out.println(o2);

    }

    private static void safe() {
        HungrySingleIns instance1 = HungrySingleIns.getInstance();
        HungrySingleIns instance2 = HungrySingleIns.getInstance();
        HungrySingleIns instance3 = HungrySingleIns.getInstance();
        System.out.println(instance1);
        System.out.println(instance2);
        System.out.println(instance3);

        LazySingleIns instance4 = LazySingleIns.getInstance();
        LazySingleIns instance5 = LazySingleIns.getInstance();
        LazySingleIns instance6 = LazySingleIns.getInstance();
        System.out.println(instance4);
        System.out.println(instance5);
        System.out.println(instance6);
    }

    private static void unsafe02() throws Exception {
        Class<?> HSIns = Class.forName("com.aizhong.singleIns.LazySingleIns");
        Constructor<?> constructor = HSIns.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object o1 = constructor.newInstance();
        Object o2 = constructor.newInstance();
        Object o3 = constructor.newInstance();
        System.out.println(o1);
        System.out.println(o2);
        System.out.println(o3);
    }

    private static void unsafe01() throws Exception {
        Class<?> HSIns = Class.forName("com.aizhong.singleIns.HungrySingleIns");
        Constructor<?> constructor = HSIns.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object o1 = constructor.newInstance();
        Object o2 = constructor.newInstance();
        Object o3 = constructor.newInstance();
        System.out.println(o1);
        System.out.println(o2);
        System.out.println(o3);
    }
}

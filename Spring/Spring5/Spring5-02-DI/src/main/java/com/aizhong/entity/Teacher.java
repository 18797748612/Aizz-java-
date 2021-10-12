package com.aizhong.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Teacher implements InitializingBean, DisposableBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Teacher.afterPropertiesSet");
    }

    public void myInitMethod() {
        System.out.println("Teacher.myInitMethod");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Teacher.destroy");
    }

    public void myDestroy() {
        System.out.println("Teacher.myDestroy");
    }
}

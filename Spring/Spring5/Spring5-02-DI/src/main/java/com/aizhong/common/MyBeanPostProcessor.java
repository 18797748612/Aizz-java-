package com.aizhong.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
    // 两个方法都有默认实现

    // Spring 创建完对象，并进行注入后，可以运行 Before ⽅法进行加工；
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("MyBeanPostProcessor.postProcessBeforeInitialization");
        return bean;
    }


    // Spring 执行完对象的初始化操作后，可以运行 After ⽅法进行加工；
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("MyBeanPostProcessor.postProcessAfterInitialization");
        return bean;
    }
}

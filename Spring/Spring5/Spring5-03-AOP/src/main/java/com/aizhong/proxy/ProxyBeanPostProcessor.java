package com.aizhong.proxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *  加工原始对象，返回代理对象
 */
public class ProxyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        InvocationHandler handler = new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                System.out.println("--- beanPostProcessor log ---");
//                Object ret = method.invoke(bean, args);
//                return ret;
//            }
//        };
        return Proxy.newProxyInstance(
                ProxyBeanPostProcessor.class.getClassLoader(),
                bean.getClass().getInterfaces(),
                (Object proxy, Method method, Object[] args) -> {
                    System.out.println("--- beanPostProcessor log ---");
                    Object ret = method.invoke(bean, args);
                    return ret;
                });
    }
}

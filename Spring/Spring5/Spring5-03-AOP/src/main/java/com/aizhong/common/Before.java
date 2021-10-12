package com.aizhong.common;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class Before implements MethodBeforeAdvice {
    /**
     * 作用: 把需要运行在原始方法执行之前运行的额外功能, 书写在 before 方法中
     *
     * Method: 额外功能所增加给的那个原始方法
     * Object[]:  额外功能所增加给的那个原始方法的参数
     * Object: 额外功能所增加给的那个原始对象
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("----------method before advice log----------");
    }
}

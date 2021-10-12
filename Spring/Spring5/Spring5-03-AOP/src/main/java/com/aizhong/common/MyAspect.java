package com.aizhong.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MyAspect {

    // 定义一个切入点，方法是 public void 方法名无要求
    @Pointcut("@annotation(com.aizhong.annotation.Log)")
    public void myPointcut(){}

    @Around(value = "myPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("---- aspect log ----");
        Object ret = joinPoint.proceed();
        return ret;
    }
}

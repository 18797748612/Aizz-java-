package com.aizhong.AOP;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Pointcut("execution(* *(..))")
    public void myPointcut(){}

    @Around(value = "myPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("---- aspect log ----");
        Object ret = proceedingJoinPoint.proceed();
        return ret;
    }
}

package com.aizhong.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class Around implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 执行原方法前
        System.out.println("Around.invoke 执行原方法前");
        Object proceed = null;
        try {
            // 执行原方法
            proceed = invocation.proceed();
        } catch(Exception e) {
            System.out.println("Around.invoke 执行异常处理");
            e.printStackTrace();
        }

        // 执行原方法后
        System.out.println("Around.invoke 执行原方法后");

        // 影响返回值
        return proceed;
    }
}

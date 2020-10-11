package com.linus.spring.aop.old;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/7
 */
public class LogInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("LogInterceptor");
        return invocation.proceed();
    }
}

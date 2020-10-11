package com.linus.spring.aop.old;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/7
 */
public class LogBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, @Nullable Object target) throws Throwable {
        System.out.println("LogBeforeAdvice.before");
    }
}

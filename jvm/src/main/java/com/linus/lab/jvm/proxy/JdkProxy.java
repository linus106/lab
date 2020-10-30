package com.linus.lab.jvm.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author wangxiangyu
 * @Date 2020/10/30 18:03
 * @Description TODO
 */
public class JdkProxy implements InvocationHandler {

    private Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK动态代理，监听开始！");
        Object result = method.invoke(target, args);
        System.out.println("JDK动态代理，监听结束！");
        return result;
    }

    public Object getEnhancedProxy(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(JdkProxy.class.getClassLoader(),
                Target.class.getInterfaces(), this);
    }

    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy();
        Object enhancedObject = jdkProxy.getEnhancedProxy(new Target());
        ((IRun) enhancedObject).run();
        ((IWalk)enhancedObject).walk();
    }
}

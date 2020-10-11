package com.linus.spring.ioc.beanfactory;

import com.linus.spring.base.Car;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/26
 * 通过BeanFactory实现IOC容器，需要手动的注册bean定义
 * 需要是手动的创建bean(getBean)
 */
public class SpringMain {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerBeanDefinition("car", BeanDefinitionBuilder.rootBeanDefinition(Car.class).getBeanDefinition());
        System.out.println("注册完bean定义");
        beanFactory.getBean("car", Car.class).run();
    }
}

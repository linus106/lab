package com.linus.spring.ioc.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.lang.Nullable;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/26
 * getBean中的9个扩展点
 */
public class CustomBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor, MergedBeanDefinitionPostProcessor, DestructionAwareBeanPostProcessor {


    /**
     * 1-实例化前
     */
    @Nullable
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    /**
     * 2-决定构造函数
     */
    @Nullable
    @Override
    public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
        return new Constructor[0];
    }

    /**
     * 3-@Autowired @Value预解析 InjectMetadata
     */
    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {

    }

    /**
     * 4-获取早期对象-解决循环引用AOP
     */
    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        return null;
    }

    /**
     * 5-实例化后
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return false;
    }

    /**
     * 6-注入PropertyValues @Autowired在这里进行DI
     */
    @Nullable
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return null;
    }

    /**
     * 7-初始化前
     */
    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    /**
     * 8-初始化后-AOP动态代理
     */
    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    /**
     * 9-销毁前
     */
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {

    }
}

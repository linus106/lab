package com.linus.spring.aop.advisor;

import com.linus.spring.aop.Calculator;
import com.linus.spring.aop.old.LogBeforeAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/7
 */
@Configuration
public class AdvisorAopConfig {

//    @Bean
//    public LogBeforeAdvice logBeforeAdvice() {
//        return new LogBeforeAdvice();
//    }
//
//    @Bean
//    public NameMatchMethodPointcutAdvisor logAspectAdvisor() {
//        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
//        advisor.setAdvice(logBeforeAdvice());
//        advisor.setMappedNames("add");//方法名
//        return advisor;
//    }
//
//
//    @Bean
//    public ProxyFactoryBean calculatorProxy(Calculator calculator) {
//        ProxyFactoryBean bean = new ProxyFactoryBean();
//        bean.setTarget(calculator);
//        bean.setInterceptorNames("logAspectAdvisor");
//        return bean;
//    }
//
//
//    @Bean
//    public BeanNameAutoProxyCreator autoProxyCreator() {
//        BeanNameAutoProxyCreator bean = new BeanNameAutoProxyCreator();
//        bean.setBeanNames("calc*");
//        bean.setInterceptorNames("logAspectAdvisor");
//        return bean;
//    }
}

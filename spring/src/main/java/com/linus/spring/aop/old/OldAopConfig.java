package com.linus.spring.aop.old;

import com.linus.spring.aop.Calculator;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/7
 */
@Configuration
public class OldAopConfig {

//    @Bean
//    public LogBeforeAdvice logBeforeAdvice() {
//        return new LogBeforeAdvice();
//    }
//
//    @Bean
//    public LogInterceptor logInterceptor() {
//        return new LogInterceptor();
//    }
//
//
//    @Bean
//    public ProxyFactoryBean calculatorProxy(Calculator calculator) {
//        ProxyFactoryBean bean = new ProxyFactoryBean();
//        bean.setTarget(calculator);
//        bean.setInterceptorNames("logInterceptor", "logBeforeAdvice");
//        return bean;
//    }
}

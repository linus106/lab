package com.linus.spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/25
 */
public class SpringMain {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
//        context.getBean("calculatorProxy", Calculator.class).add(1,2);//spring老版本的aop实现，基于接口和FactoryBean实现
        ICalculator calculator = context.getBean("calculator", ICalculator.class);
        calculator.add(1,2);
    }
}

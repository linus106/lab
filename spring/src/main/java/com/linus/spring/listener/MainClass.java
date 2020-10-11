package com.linus.spring.listener;

import com.linus.spring.base.Car;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/5
 */
public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);

        context.publishEvent(new CustomEvent(new Object()));
    }
}

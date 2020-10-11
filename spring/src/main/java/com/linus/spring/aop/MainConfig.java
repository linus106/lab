package com.linus.spring.aop;

import com.linus.spring.base.Car;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/25
 */
@EnableAspectJAutoProxy
//@ComponentScan({"com.linus.spring.aop", "com.linus.spring.aop.old"})
@EnableTransactionManagement
@ComponentScan({"com.linus.spring.aop"})
public class MainConfig {

}

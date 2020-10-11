package com.linus.spring.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/28
 */
@Component
public class Driver {

    @Autowired
    private Car car;


    public Driver() {
        System.out.println("driver created");
    }

    public void drive() {
        System.out.println("driver drives");
    }
}

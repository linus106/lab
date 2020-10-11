package com.linus.spring.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/26
 */
@Component
public class Car {

    @Autowired
    private Driver driver;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Car() {
        System.out.println("car created");
    }

    public void run() {
        System.out.println("car runs");
    }
}

package com.linus.spring.aop;

import org.springframework.stereotype.Service;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/25
 */
@Service
public class Calculator implements ICalculator{

    public int add(int a, int b) {
        int result = a + b;
        System.out.println("Calculator.add:" + a + "+" + b +"=" + result);
        return result;
    }
}

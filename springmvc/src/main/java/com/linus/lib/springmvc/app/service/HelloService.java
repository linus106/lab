package com.linus.lib.springmvc.app.service;

import com.linus.lib.springmvc.framework.annotation.LabService;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/18
 */
@LabService("helloService")
public class HelloService implements IHelloService{

    public String hello() {
        System.out.println("hello");
        return "hello.jsp";
    }
}

package com.linus.lib.springmvc.app.controller;

import com.linus.lib.springmvc.framework.annotation.LabAutoWired;
import com.linus.lib.springmvc.app.service.HelloService;
import com.linus.lib.springmvc.framework.annotation.LabController;
import com.linus.lib.springmvc.framework.annotation.LabRequestMapping;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/16
 */
@LabController
public class HelloController {

    @LabAutoWired("helloService")
    private HelloService helloService;


    @LabRequestMapping("/hello")
    public String hello() {
        return helloService.hello();
    }
}

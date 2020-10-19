package com.linus.lab.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/19
 */
public class HelloListener implements ServletContextListener {

    public HelloListener() {
        System.out.println("instant HelloListener");
    }

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("HelloListener called!");
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}

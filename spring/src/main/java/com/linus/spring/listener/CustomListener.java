package com.linus.spring.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/5
 */
@Component
public class CustomListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("CustomListener 处理事件");
    }


    @EventListener(ContextRefreshedEvent.class)
    public void onContextRefresh(ContextRefreshedEvent event) {
        System.out.println("容器刷新完了");

    }
}

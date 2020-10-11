package com.linus.spring.listener;

import org.springframework.context.ApplicationEvent;

import java.util.EventObject;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/5
 */
public class CustomEvent extends ApplicationEvent {

    public CustomEvent(Object source) {
        super(source);
    }
}

package com.linus.lib.springmvc.framework.handler;

import java.lang.reflect.Method;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/18
 */
public class LabServletHandler {

    private String url;

    private Object handler;

    private Method method;

    public LabServletHandler(String url, Object handler, Method method) {
        this.url = url;
        this.handler = handler;
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getHandler() {
        return handler;
    }

    public void setHandler(Object handler) {
        this.handler = handler;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}

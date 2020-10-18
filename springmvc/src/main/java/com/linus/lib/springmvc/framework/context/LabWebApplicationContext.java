package com.linus.lib.springmvc.framework.context;

import com.linus.lib.springmvc.framework.annotation.LabAutoWired;
import com.linus.lib.springmvc.framework.annotation.LabController;
import com.linus.lib.springmvc.framework.annotation.LabService;
import com.linus.lib.springmvc.framework.xml.LabXmlParser;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/18
 * springmvc 容器
 */
public class LabWebApplicationContext {

    private String contextConfigLocation;

    private List<String> classNameList = new ArrayList<>();

    public Map<String, Object> singletonObjects = new HashMap<>();

    public LabWebApplicationContext(String contextConfigLocation) {
        this.contextConfigLocation = contextConfigLocation;
    }


    public final void onRefresh() {
        //1 获取配置
        String base = LabXmlParser.getBasePackage(this.contextConfigLocation.split(":")[1]);
        String[] packs = base.split(",");
        //2 包扫描
        for (String pack : packs) {
            excuteScanPackage(pack);
        }
        //3 实例化
        excuteInstantiation();
        //4 自动注入
        excuteAutoWired();
    }



    /**
     * 自动注入
     */
    public void excuteAutoWired() {
        try {
            for (Map.Entry<String, Object> entry : singletonObjects.entrySet()) {

                Object source = entry.getValue();
                Class<?> clazz = source.getClass();
                if (clazz.isAnnotationPresent(LabController.class)) {
                    for (Field field : clazz.getDeclaredFields()) {
                        if (field.isAnnotationPresent(LabAutoWired.class)) {
                            LabAutoWired autoWired = field.getAnnotation(LabAutoWired.class);
                            Object target = singletonObjects.get(autoWired.value());
                            field.setAccessible(true);
                            field.set(source, target);
                        }
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 实例化
     */
    public void excuteInstantiation() {
        try {
            for (String className : classNameList) {

                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(LabController.class)) {
                    String beanName = clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1);
                    singletonObjects.put(beanName, clazz.newInstance());
                } else if (clazz.isAnnotationPresent(LabService.class)) {
                    LabService labService = clazz.getAnnotation(LabService.class);
                    String beanName = labService.value();
                    singletonObjects.put(beanName, clazz.newInstance());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 包扫描
     */
    public void excuteScanPackage(String pack) {
        URL url = this.getClass().getClassLoader().getResource("/" + pack.replaceAll("\\.", "/"));
        String path = url.getFile();
        File dir = new File(path);
        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {
                excuteScanPackage(pack + "." + f.getName());
            } else {
                String className = pack + "." + f.getName().replaceAll(".class", "");
                this.classNameList.add(className);
            }
        }

    }
}

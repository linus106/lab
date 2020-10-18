package com.linus.lib.springmvc.framework.annotation;

import java.lang.annotation.*;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/18
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LabAutoWired {

    String value() default "";
}

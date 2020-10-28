package com.linus.lab.mybatis.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.util.Properties;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/28
 */
@Intercepts({@Signature(
        type= Executor.class,
        method = "update",
        args = {MappedStatement.class,Object.class})})
public class LogPlugin implements Interceptor {
    private Properties properties = new Properties();
    public Object intercept(Invocation invocation) throws Throwable {



        // implement pre processing if need
        Object returnObject = invocation.proceed();
        // implement post processing if need

        System.out.println("update triggered plugin! result = " + returnObject);
        return returnObject;
    }
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}


/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/19
 *
 * java web 一种sun定义的动态网页规范，由tomcat实现
 *
 * servlet    取决于load-on-start-up属性 调用DispatcherServlet的init创建spring-servlet-web容器
 * filter     web容器初始化时创建
 * listener   web容器初始化时创建         创建spring-root-web容器
 *
 * 父子容器设计：解耦，spring-mvc可以替换成struts
 *
 */
package com.linus.lab.servlet;
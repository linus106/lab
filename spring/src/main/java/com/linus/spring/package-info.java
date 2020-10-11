/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/26
 * 脑图：https://www.processon.com/view/link/5f5075c763768959e2d109df
 * 1 Spring体系架构
 * 1.1 SpringFrameWork Runtime
 * ---Data Access---    -----------Web---------
 * JDBC     ORM         WebSocket       Servlet
 * OXM      JMS
 * Transactions         Web             Portlet
 *
 * ---------Support:面向切面&消息-----------------
 * AOP      Aspects     Instrumentation Messaging
 *
 * -------Core Container:IOC|DI特性------------
 * Beans    Core        Context         SpEL
 *
 * 1.2 maven依赖关系
 *             ->spring-core       -> spring-aop -> spring-core
 *            /                  /
 * spring-boot-> spring-context - > spring-core
 *                              \
 *                               -> spring-beans -> spring-core
 * (1)万物皆依赖spring-core，里边包括asm、cglib等通用组件
 * (2)BeanFactory定义在spring-beans,负责创建bean。子类实现了BeanDefinitionRegistry,可以进行bean定义。
 * 可以推断出spring-beans是bean定义和创建的模块
 * (3)ApplicationContext定义在spring-context,负责IOC容器内bean的生命周期管理。包括事件、注解、aware、PostProcessor。
 * 可以看出spring-context是IOC容器的管理
 *
 * 2 IOC-见ioc包
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package com.linus.spring;
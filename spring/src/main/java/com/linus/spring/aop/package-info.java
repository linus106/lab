/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/7
 *
 * spring-Aop依赖aspectJ，概念以及pointcut解析复用了aspectJ，但因为aspectJ比较重，需要有特定的编译器（编译期织入），所以其他部分spring是自己做的。
 * AOP概念
 * 1 target---要增强的目标对象
 * 2 aspect---切面，强调关注点的模块化，例子是事务管理。
 * 3 join point---连接点，代表增强的方法
 * 4 point cut---匹配连接点的断言
 * 5 advice---通知，一种增强
 * 6 weave---织入，将通知增强到目标对象的方法上
 * 7 advisor---是对advice和pointcut的包装
 *
 * 步骤
 * 1 解析切面---AbstractAutoProxyCreator.postProcessBeforeInstantiation--为什么每次都要解析切面，都是从缓存中拿，兼容老的方式
 *          1.1 shouldSkip---遍历所有bean定义找到@Aspect注解（也支持接口方式），把里边的方法都转化成advisors
 * 2 生成代理对象---postProcessAfterInitialization（正常bean在初始化之后，循环依赖的在getSingleton获取早期对象的时候）
 *          2.1 proxyTagetClass设置了就会用cglib代理，如果没有设置并且有接口就会用jdk动态代理
 * 3 动态代理的调用
 *
 *
 * AOPContext可以获取当前的动态代理对象，通过threadlocal实现
 *
 */
package com.linus.spring.aop;
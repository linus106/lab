/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/26
 *
 *
 *
 * IOC
 * 1 基础概念
 * 1.1 BeanFactory
 * 简单工厂模式-Object getBean(String name)
 * 根据bean的名称创建对应的bean
 *
 * 1.2 BeanDefinition
 * 一个bean定义包括：beanClass|scope|lazyInit等等属性，用来描述创建一个什么样的bean
 *
 * 1.3 BeanDefinitionRegistry
 * 注册bean定义-registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
 *
 * 1.4 BeanDefinitionReader
 * 读取用户配置-loadBeanDefinitions(resources|locations)
 *
 * 1.5 ClassPathBeanDefinitionScanner
 * 按照用户配置扫描bean定义-Set<BeanDefinitionHolder> doScan(String... basePackages)
 *
 * 1.6 ApplicationContext
 * 和BeanFactory单一的创建bean职责相比，都可以作为容器。但是ApplicationContext增加了：
 * 生命周期管理|自动注册BeanPostProcessor(BeanFactory需要显示调用addBeanPostProcessor)|自动注册BeanFactoryPostProcessor
 * |MessageSource(国际化)|ApplicationEvent等功能
 *
 * 2 加载及生命周期-参考{@see MainConfig.class}
 *
 * 2.1 注册bean定义
 * 配置(XML|注解|类)-------reader|scanner------>BeanDefinition------registerBeanDefinition--->ApplicationContext
 *
 * 2.2 bean生命周期-getBean
 * BeanDefinition-> 实例化(反射|工厂方法FactoryBean-@Bean)->填充属性(populateBean)->初始化->单例池-一级缓存
 *
 * 4 扩展点
 * 4.1 BeanFactoryPostProcessor-可以去增删改bean定义
 * void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
 * 子接口-BeanDefinitionRegistryPostProcessor-可以用来注册bean定义
 * void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
 *
 * 4.2 BeanPostProcessor-getBean中有9处扩展点
 * 子接口-SmartInstantiationAwareBeanPostProcessor, MergedBeanDefinitionPostProcessor, DestructionAwareBeanPostProcessor-声明了9个扩展点
 * 实例化前后   初始化前后  选择构造函数
 *
 * 4.3 Aware-基于PostProcessor上实现的更细致的扩展点
 * 4.3.1 AbstractAutowireCapableBeanFactory.invokeAwareMethods----bean的初始化阶段
 * <li>BeanNameAware's {@code setBeanName}
 * <li>BeanClassLoaderAware's {@code setBeanClassLoader}
 * <li>BeanFactoryAware's {@code setBeanFactory}
 *
 * 4.3.2 ApplicationContextAwareProcessor.invokeAwareInterfaces----bean初始化之前,BeanPostProcessor.postProcessBeforeInitialization
 * <li>EnvironmentAware's {@code setEnvironment}
 * <li>EmbeddedValueResolverAware's {@code setEmbeddedValueResolver}
 * <li>ResourceLoaderAware's {@code setResourceLoader}
 * (only applicable when running in an application context)
 * <li>ApplicationEventPublisherAware's {@code setApplicationEventPublisher}
 * (only applicable when running in an application context)
 * <li>MessageSourceAware's {@code setMessageSource}
 * (only applicable when running in an application context)
 * <li>ApplicationContextAware's {@code setApplicationContext}
 * (only applicable when running in an application context)
 *
 * 4.3.3
 * <li>ServletContextAware's {@code setServletContext}
 * (only applicable when running in a web application context)
 * <li>{@code postProcessBeforeInitialization} methods of BeanPostProcessors
 * <li>InitializingBean's {@code afterPropertiesSet}
 * <li>a custom init-method definition
 * <li>{@code postProcessAfterInitialization} methods of BeanPostProcessors
 * </ol>
 *
 * 5 getBean 3级缓存
 * 一级缓存:保存最终创建好的bean
 * 二级缓存:在循环依赖的前提下，保存早期bean(代理对象或普通对象)。
 * 三级缓存:在循环依赖的前提下，保存factorybean，也就是一种bean的创建方法。
 * 在循环依赖前提下，先添加到三级缓存，创建出bean之后再添加到二级缓存，执行完populate等一系列逻辑之后，得到最终的bean放到一级缓存。
 *
 * 虚幻依赖
 * getBean：
 *          1 getSingleton()---
 *                      时机：第一次getbean一开始
 *                      1.1 尝试从一级缓存中拿，如果有则直接返回；
 *                      时机：第一次getbean，初始化结束
 *                      1.2 然后尝试从二级缓存拿，如果拿到了，说明另一个getBean方法对早期bean可能进行了代理处理(三级缓存工厂方法加工后的bean)，如果有返回
 *                      时机：第二次getbean
 *                      1.3 尝试从三级缓存拿，如果拿到了，说明该bean正在创建，调用三级缓存的工厂方法得到代理后的早期bean。（同时早期bean还会在第一次getbean的过程中继续加工）
 *          2 doCreateBean()
 *                      2.1 实例化
 *                      2.2 放到三级缓存,三级缓存是一个factorybean，方法里引用了刚才创建的早期bean。
 *                      2.3 populate bean()---可能存在循环依赖了,如果有依赖，则调用getBean
 *                      2.4 init beaa()
 *                      2.5 调用getSingleton() 如果有二级缓存，尝试用二级缓存覆盖这边创建好的bean返回。
 *
 */
package com.linus.spring.ioc;
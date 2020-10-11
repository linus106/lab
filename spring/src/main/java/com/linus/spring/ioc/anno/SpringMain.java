package com.linus.spring.ioc.anno;

import com.linus.spring.base.Car;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/26
 *
 *
 *
 *
 */
public class SpringMain {

    public static void main(String[] args) {


        /**
         * 1 this()
         *      1.1 new DefaultListableBeanFactory()---创建bean工厂
         *      1.2 new AnnotatedBeanDefinitionReader(this)---创建配置类读取器
         *              1.2.1 AnnotationConfigUtils.registerAnnotationConfigProcessors()创建创世纪的处理器，比如ConfigurationClassPostProcessor用来解析配置类，注册bean定义
         *      1.3 new ClassPathBeanDefinitionScanner(this)---创建配置扫描器
         *
         * 2 register()
         *      2.1 this.reader.register(componentClasses)---注册配置类，注册到bean定义中
         *              2.1.1 new AnnotatedGenericBeanDefinition(beanClass)---生成注解类型的配置类bean定义
         *              2.1.2 this.beanFactory.registerBeanDefinition(beanName, beanDefinition)---注册bean定义，放到map中
         *
         *  3 refresh()
         *      3.1 prepareRefresh();// Prepare this context for refreshing.
         *      3.2 ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();// Tell the subclass to refresh the internal bean factory.
         *              3.2.1 refreshBeanFactory()---ClassPathXml方式下读取并注册bean定义
         *      3.3 prepareBeanFactory(beanFactory);// Prepare the bean factory for use in this context.
         *      3.4 postProcessBeanFactory(beanFactory);// Allows post-processing of the bean factory in context subclasses.
         *      3.5 invokeBeanFactoryPostProcessors(beanFactory);// Invoke factory processors registered as beans in the context.
         *              phase 1
         *              3.5.1 invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry)---调用实现了BeanDefinitionRegistryPostProcessor的BeanFactoryPostProcessors，按照PriorityOrdered>Ordered>未声明的顺序，
         *              间隔中要重新加载所有bean定义来感知到前面BeanDefinitionRegistryPostProcessor的影响，其中未声明的部分，每个BeanDefinitionRegistryPostProcessor之间都要重新加载bean定义。
         *              3.5.2 invokeBeanFactoryPostProcessors(registryProcessors, beanFactory)---调用实现了BeanDefinitionRegistryPostProcessor接口的postProcessBeanFactory方式
         *              3.5.3 invokeBeanFactoryPostProcessors(regularPostProcessors, beanFactory)---调用未实现BeanDefinitionRegistryPostProcessor接口，只实现了BeanFactoryPostProcessor接口的postProcessBeanFactory方式
         *
         *              phase 2
         *              3.5.4 invokeBeanFactoryPostProcessors(nonOrderedPostProcessors, beanFactory)---调用一阶段新产生的BeanFactoryPostProcessor，同理按照PriorityOrdered>Ordered>未声明的顺序调用。
         *              没有实例化的后置处理器会直接通过beanFactory.getBean()来创建
         *      3.6 registerBeanPostProcessors(beanFactory);// Register bean processors that intercept bean creation.
         *              3.6.1 从bean定义的map中找到BeanPostProcessor的bean定义，按照PriorityOrdered>Ordered>未声明的顺序通过getBean实例化
         *              3.6.2 registerBeanPostProcessors(beanFactory, priorityOrderedPostProcessors)---放到AbstractBeanFactoryBeanFactory的beanPostProcessors列表中
         *
         *      3.7 initMessageSource();
         *      3.8 initApplicationEventMulticaster();
         *      3.9 onRefresh();
         *      3.10 registerListeners();
         *      3.11 finishBeanFactoryInitialization(beanFactory);// Instantiate all remaining (non-lazy-init) singletons.
         *              3.11 beanFactory.freezeConfiguration();---冻结bean定义
         *              3.11.2 循环所有的bean定义
         *                      3.11.2.1 getMergedLocalBeanDefinition(beanName)---TODO ???
         *                      3.11.2.2 if (!bd.isAbstract() && bd.isSingleton() && !bd.isLazyInit()) ---判断是否符合要标准，不是抽象类&& 是单例 && 不是懒加载
         *                      3.11.2.3 if (isFactoryBean(beanName))---判断是否是factoryBean
         *                      3.11.2.4 getBean(beanName);---调用getBean，开始创建bean，详细见4
         *              3.11.3 循环调用实现了SmartInitializingSingleton接口的bean的afterSingletonsInstantiated方法，是所有bean都创建完扩展点
         *      3.12 finishRefresh();
         *
         *
         *  4 getBean()详解
         *          4.1 getSingleton(beanName)---尝试从一级缓存中拿
         *          4.2 子父容器---TODO ???
         *          4.3 处理dependsOn,先创建依赖的希望在目前bean前面创建的bean
         *          4.4 getSingleton(beanName, () -> {return createBean(beanName, mbd, args);} ---把我们的bean标记正在创建，然后创建
         *                  4.4.1 通过singletonsCurrentlyInDestruction这个Set把我们的bean标记正在创
         *                  4.4.2 createBean(beanName, mbd, args)
         *                          4.4.2.1 Class<?> resolvedClass = resolveBeanClass(mbd, beanName);---判断bean定义中的类是否可以存在、可以解析
         *                          4.4.2.2 Object bean = resolveBeforeInstantiation(beanName, mbdToUse);if (bean != null) {return bean;}---调用InstantiationAwareBeanPostProcessor的postProcessBeforeInstantiation扩展点方法,创建advisors并调用advisors的AfterInitial方法
         *                          4.4.2.3 instanceWrapper = createBeanInstance(beanName, mbd, args);---实例化bean，先调用SmartInstantiationAwareBeanPostProcessor的
         *                          determineCandidateConstructors来选择构造方法，然后用instantiateBean(beanName, mbd);来通过反射实例化bean。
         *                          用到了装饰器的设计模式
         *                          4.4.2.4 if(isSingletonCurrentlyInCreation(beanName)) {addSingletonFactory(beanName, () -> getEarlyBeanReference(beanName, mbd, bean));}---
         *                          如果是正在创建循环依赖的bean,则添加三级缓存、删除二级缓存。
         *                          4.4.2.5 populateBean(beanName, mbd, instanceWrapper);---填充属性，@Autowired调用一系列后置处理器：
         *                          (1)InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation---实例化后的后置处理器
         *                          (2)InstantiationAwareBeanPostProcessor.postProcessProperties|postProcessPropertyValues---属性赋值
         *                          4.4.2.6 exposedObject = initializeBean(beanName, exposedObject, mbd);---初始化,调用初始化前后的
         *                          (1)BeanPostProcessor.postProcessBeforeInitialization
         *                          (2)InitializingBean.afterPropertiesSet();
         *                          (3)init-method @PostConstruct
         *                          (4)BeanPostProcessor.postProcessAfterInitialization
         *                          4.4.2.7 registerDisposableBeanIfNecessary TODO
         *                  4.4.3 addSingleton(beanName, singletonObject);---把创建好的bean放到一级缓存，同时删掉二三级缓存
         */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);


        context.getBean("car", Car.class).run();
    }

}

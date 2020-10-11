package com.linus.spring.ioc.anno;

import com.linus.spring.base.Car;
import com.linus.spring.base.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 *
 * 由ConfigurationClassPostProcessor负责解析
 *
 * 1 postProcessBeanFactory
 * 对所有的Configuration注解 会生成动态代理，从容器中拿。 新的动态代理类替换旧的原生bean定义。
 *
 * 2 postProcessBeanDefinitionRegistry
 * 注册bean定义
 *
 *
 * do---循环调用，因为解析配置类，可能发现新的配置类
 * ConfigurationClassParser.doProcessConfigurationClass---解析单个配置类
 * 注解的处理顺序：@PropertySources->@ComponentScans->@Import->@ImportResource->@Bean
 *          ComponentScanAnnotationParser.parse---解析单个@ComponentScan
 *          根据注解的配置创建一个ClassPathBeanDefinitionScanner，然后调用doScan()
 *          扫描路径里带有注解的类：@Component  @ComponentScan  @Import  @ImportResource  @Bean
 *          生成bean定义，注册到容器中
 * while (存在候选配置类)
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.linus.spring.base")
public class MainConfig {

//    @Bean
//    public Car car() {
//        Car car =  new Car();
//        car.setDriver(driver());//会从容器中拿，不会而外创建
//        return car;
//    }
//
//    @Bean
//    public Driver driver() {
//        return new Driver();
//    }
}

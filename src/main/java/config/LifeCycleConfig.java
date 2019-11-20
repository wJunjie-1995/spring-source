package config;

import bean.lifecycle.ManuallySpecifiedInitAndDes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;

/**
 * @author galileo
 * @date 2019/8/1 15:36
 *
 * bean生命周期：创建---初始化---销毁
 * 1、创建：
 *      1.单实例：容器创建；
 *      2.多实例：获取对象
 * 2、初始化：
 *      1.单实例：容器创建；
 *      2.多实例：获取对象
 * 3、销毁
 *      1.单实例：容器关闭；
 *      2.多实例：容器不管销毁
 * 方式：
 * （1）手动指定初始化和销毁方法：在@Bean（initMethod=？，destroyMethod=？）
 * （2）让Bean实现接口InitializingBean、DisposableBean
 * （3）使用注解（来自JSR250）：@PostConstruct，@PreDestroy
 * （4）BeanPostProcessor后置处理器：
 *
 */
@ComponentScan("bean.lifecycle")

public class LifeCycleConfig {
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public ManuallySpecifiedInitAndDes car(){
        return new ManuallySpecifiedInitAndDes();
    }
}

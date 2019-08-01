package config;

import bean.lifecycle.ManuallySpecified;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
 * （2）让Bean实现接口
 */
@ComponentScan("bean")
public class LifeCycleConfig {
    @Scope("prototype")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public ManuallySpecified car(){
        return new ManuallySpecified();
    }
}

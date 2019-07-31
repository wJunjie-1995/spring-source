package config;

import bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author galileo
 * @date 2019/7/31 15:37
 */
@Configuration
@ComponentScan("controller")
public class MainConfig {

    /**
     * 单实例模式时,对象在容器创建时创建
     * 多实例模式时，对象在从容器中获得时创建
     */
    @Scope("prototype ")
    @Bean
    public Person person(){
        return new Person(1,"aaa");
    }
}

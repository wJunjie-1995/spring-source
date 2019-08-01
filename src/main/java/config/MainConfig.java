package config;

import bean.Color;
import bean.Person;
import bean.Red;
import condition.LinuxCondition;
import condition.MyImportBeanDefinitionRegistrar;
import condition.MyImportSelector;
import condition.WindowsCondition;
import org.springframework.context.annotation.*;

/**
 * 总结：
 * 容器注册方式：
 * 1、【自己写的类】包扫描or组件标注注解（@Component）
 * 2、【第三方包】 @Bean，
 * 3、【第三方包】 @Import:快速为容器导入组件
 *      1.Import,
 *      2.ImportSelector,返回需要导入的组件的全类名(springBoos 常用 )
 *      3.ImportBeanDefinitionRegistrar,手动注册到容器中，使用BeanDefinitionRegistry
 * 4、Spring提供的 FactoryBean：
 *      1.默认是得到工厂Bean的getObject方法返回的对象
 *      2.若要获得工厂Bean本身，在“”中加上前缀&
 *
 * @author galileo
 * @date 2019/7/31 15:37
 *
 * 当Conditional注解放在类上时，类中所有bean都符合条件才装配，即统一设置
 *
 */
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
@Configuration
@ComponentScan("controller")
public class MainConfig {

    /**

     *
     *
     * 单实例模式时,对象在容器启动时创建
     * 多实例模式时，对象在从容器中获得时创建（@Scope("prototype")）
     *
     * 懒加载：容器启动时不加载对象，而是在第一次获取时再创建对象，并初始化。(@Lazy)
     */
    @Bean
    public Person person(){
        System.out.println("bean person 创建");
        return new Person(1,"aaa");
    }

    /**
     * 条件注解：
     */
    @Conditional(WindowsCondition.class)
    @Bean("Bill")
    public Person person01(){
        System.out.println("bean bill 创建");
        return new Person(2,"bill");
    }

    @Conditional(LinuxCondition.class)
    @Bean("Linus")
    public Person person02(){
        System.out.println("bean Linus 创建");
        return new Person(3,"Linus");
    }
}

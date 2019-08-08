import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import bean.property.Liu;
import config.LifeCycleConfig;
import config.MainConfig;
import config.PropertyConfig;

/**
 * @author galileo
 * @date 2019/7/31 15:47
 */
public class MyTest {
    /** 创建IOC容器 */
    private AnnotationConfigApplicationContext applicationContext ;

    @Test
    public void registryTest(){
        applicationContext =
                new AnnotationConfigApplicationContext(MainConfig.class);
        System.out.println("--容器启动完毕--");

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames){
            System.out.println(name);
        }
    }

    @Test
    public void lifeCycleTest(){
        applicationContext =
                new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        System.out.println("容器创建ok");
        System.out.println("开始获取对象");
        applicationContext.getBean("car");
        //关闭容器
        applicationContext.close();
    }

    @Test
    public void propertyTest(){
        applicationContext =
                new AnnotationConfigApplicationContext(PropertyConfig.class);
        System.out.println("容器创建ok");
        System.out.println("开始获取对象");
        Liu liu = (Liu) applicationContext.getBean("liu");
        System.out.println(liu.toString());

        System.out.println("从运行环境中读取Property");
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("liu.nickName");
        System.out.println("liu.nickName:"+property);
        //关闭容器
        applicationContext.close();
    }
}

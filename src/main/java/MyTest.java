import config.LifeCycleConfig;
import config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
}

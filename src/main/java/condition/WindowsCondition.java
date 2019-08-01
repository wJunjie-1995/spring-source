package condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author galileo
 * @date 2019/8/1 10:19
 */
public class WindowsCondition implements Condition {
    /**
     *
     * @param context 上下文环境
     * @param metadata 注释信息
     */
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //1、可获得IOC的Bean工厂
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //2、类加载器
        ClassLoader classLoader = context.getClassLoader();
        //3、获取当前运行环境信息
        Environment environment = context.getEnvironment();
        //4、获取bean的注册类,可用于判断当前容器中bean的注册情况
        BeanDefinitionRegistry registry = context.getRegistry();

        //return registry.containsBeanDefinition("person");

        //获取操作系统
        String property = environment.getProperty("os.name");

        return property.contains("Windows");
    }
}

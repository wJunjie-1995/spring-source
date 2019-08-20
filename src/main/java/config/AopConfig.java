package config;

import bean.aop.LogAspects;
import bean.aop.MathCalculator;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author galileo
 * @date 2019/8/20 8:53
 *
 * AOP（动态代理）:在程序运行期间动态地将代码切入到指定方法指定位置进行运行
 *  1、导入Aop模块（spring-aspects）
 *  2、创建业务逻辑类（MathCalculator）,在其运行时打印日志（方法运行前、后、出现异常时）
 *  3、定义一个日志切面类（LogAspects），其方法需要动态感知 MathCalculator.div 运行到哪一步，然后执行
 *      通知方法：前置、后置、返回、异常、环绕（最低层：动态代理，手动推进目标方法进行：joinPoint.proceed）
 *  4、给切面类的目标方法标注注解(通知注解)
 *  5、将切面类和业务逻辑类都加入到容器中
 *  6、告知Spring哪个类是切面类（@Aspect）
 *  [7!]、加上EnableAspectJAutoProxy，开启基于注解的切面模式
 *
 *  Aop原理：
 *  EnableAspectJAutoProxy：
 *  1、什么是EnableAspectJAutoProxy？
 *      1）'@EnableAspectJAutoProxy'注解为容器导入了AspectJAutoProxyRegistrar：@Import(AspectJAutoProxyRegistrar.class)
 *
 *      而AspectJAutoProxyRegistrar 实现了接口 ImportBeanDefinitionRegistrar，使用BeanDefinitionRegistry
 *          为容器添加了AspectJAnnotationAutoProxyCreator：“AopConfigUtils.registerAspectJAnnotationAutoProxyCreatorIfNecessary(registry);”
 *      AopConfigUtils.registerOrEscalateApcAsRequired注册 AspectJAwareAdvisorAutoProxyCreator 类到容器中，其bean的名字
 *          是internalAutoProxyCreator（AUTO_PROXY_CREATOR_BEAN_NAME = "org.springframework.aop.config.internalAutoProxyCreator"）
 *      2）获取EnableAspectJAutoProxy注解的内容：proxyTargetClass和exposeProxy根据其值是true还是false做相应的操作
 *  2、关键组件 AspectJAwareAdvisorAutoProxyCreator 解析：
 *      继承关系：AspectJAwareAdvisorAutoProxyCreator
 *              ->AspectJAwareAdvisorAutoProxyCreator
 *              ->AbstractAdvisorAutoProxyCreator
 *              ->AbstractAutoProxyCreator implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *                  关注两个接口： 1）后置处理器接口 2）Bean自动装配接口 BeanFactory
 *
 *
 *
 */
@EnableAspectJAutoProxy
@Configurable
public class AopConfig {
    @Bean
    public MathCalculator mathCalculator(){
        return new MathCalculator();
    }

    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }
}

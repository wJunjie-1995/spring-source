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

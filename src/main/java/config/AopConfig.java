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
 *          为容器添加了 AspectJAnnotationAutoProxyCreator：“AopConfigUtils.registerAspectJAnnotationAutoProxyCreatorIfNecessary(registry);”
 *      AopConfigUtils.registerOrEscalateApcAsRequired注册 AspectJAwareAdvisorAutoProxyCreator 类到容器中，其bean的名字
 *          是internalAutoProxyCreator（AUTO_PROXY_CREATOR_BEAN_NAME = "org.springframework.aop.config.internalAutoProxyCreator"）
 *      2）获取EnableAspectJAutoProxy注解的内容：proxyTargetClass和exposeProxy根据其值是true还是false做相应的操作
 *  2、关键组件 AnnotationAwareAspectJAutoProxyCreator （会在任何Bean创建之前先尝试返回Bean的实例）解析：
 *      继承关系：AnnotationAwareAspectJAutoProxyCreator
 *              ->AspectJAwareAdvisorAutoProxyCreator
 *              ->AbstractAdvisorAutoProxyCreator
 *              ->AbstractAutoProxyCreator implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *                  关注两个接口： 1）后置处理器接口 2）Bean自动装配接口 BeanFactory
 *
 * 断点处：
 *       AbstractAutoProxyCreator.setBeanFactory()、
 *       后置处理器逻辑：
 *       AbstractAutoProxyCreator.postProcessBeforeInstantiation（Class<?> beanClass, String beanName）
 *       AbstractAutoProxyCreator.postProcessAfterInitialization(Object bean, String beanName)
 *
 *       AbstractAdvisorAutoProxyCreator.重写setBeanFactory() -> initBeanFactory(ConfigurableListableBeanFactory beanFactory)
 *
 *       AspectJAwareAdvisorAutoProxyCreator无
 *
 *       AnnotationAwareAspectJAutoProxyCreator.重写initBeanFactory(ConfigurableListableBeanFactory beanFactory)
 *
 *  调用流程：
 *      1、传入配置类（AopConfig.class），创建IOC容器
 *      2、注册配置类；调用refresh()刷新容器：即创建所有容器中的Bean、所有功能准备好
 *      3、在refresh()中：registerBeanPostProcessors(beanFactory)，注册bean的后置处理器，来方便拦截bean的创建：
 *          1）获取IOC容器中已经定义了的需要创建对象的所有BeanPostProcessor后置处理器
 *              （beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false)）
 *              注：注解‘@EnableAspectJAutoProxy’的Import一个注册类会引发一系列操作导致 AnnotationAwareAspectJAutoProxyCreator 传入后置处理器
 *          2）在容器中添加了些其他的 BeanPostProcessor：beanFactory.
 *              addBeanPostProcessor(new BeanPostProcessorChecker(beanFactory, beanProcessorTargetCount))
 *          3）优先注册实现了PriorityOrdered的BeanPostProcessor、其次是Ordered、最后是什么都没实现的。
 *              注：internalAutoProxyCreator，即 AnnotationAwareAspectJAutoProxyCreator，实现了Ordered接口。
 *          4)注册 BeanPostProcessor，实际上就是创建 BeanPostProcessor对象，再保存在容器中；
 *              创建internalAutoProxyCreator的BeanPostProcessor对象【AnnotationAwareAspectJAutoProxyCreator】：
 *              1，创建bean实例：createBeanInstance(beanName, mbd, args);
 *              2，为bean属性赋值：populateBean(beanName, mbd, instanceWrapper);
 *              3，初始化bean：initializeBean(beanName, exposedObject, mbd)
 *                  1,处理Aware接口方法回调，即实现了BeanFactoryAware需要 setBeanFactory，invokeAwareMethods(beanName, bean)
 *                  2,应用后置处理器BeforeInitialization，applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName)
 *                  3,执行初始化，invokeInitMethods(String beanName, final Object bean, RootBeanDefinition mbd)
 *                  4,应用后置处理器AfterInitialization，applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
 *              4，BeanPostProcessor【AnnotationAwareAspectJAutoProxyCreator】  创建成功 -->aspectJAdvisorsBuilder(？)
 *           5）把BeanPostProcessor注册到beanFactory中：beanFactory.addBeanPostProcessor(postProcessor)
 *
 *      =================以上为创建和注册 AnnotationAwareAspectJAutoProxyCreator 的过程==================
 *
 *      AnnotationAwareAspectJAutoProxyCreator 的后置处理器 -> postProcessBeforeInstantiation
 *      4、完成bean的初始化：finishBeanFactoryInitialization(beanFactory)；创建剩下的单实例
 *          1）遍历容器中所有的bean，依次创建对象getBean（beanName）
 *              getBean()->doGetBean()->getSingleton()
 *          2)创建bean
 *          【AnnotationAwareAspectJAutoProxyCreator 在所有Bean被创建之前都会有一个拦截，由于其实现了 InstantiationAwareBeanPostProcessor，
 *              会调用方法postProcessBeforeInstantiation（）】
 *              1、先从缓存中获取当前bean，如果能获取到，说明以前被创建过，则可以直接使用；否则再创建
 *                  只要创建好的bean都会被缓存起来。这种方式保证了整个容器的单实例机制。
 *              2、createBean();创建bean
 *              注：后置处理器的区分：【BeanPostProcessor是在Bean对象创建完成初始化前后调用】
 *                               【InstantiationAwareBeanPostProcessor 是在创建Bean实例之前先尝试用后置处理器返回对象】
 *                  1）解析BeforeInstantiation： resolveBeforeInstantiation(beanName, mbdToUse)
 *                      希望后置处理器在此能返回一个代理对象；如果能返回就直接使用，不能就继续创建
 *                      1、后置处理器先尝试返回对象；
 *                          bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 *                          遍历所有后置处理器，若是 InstantiationAwareBeanPostProcessor，则执行 postProcessBeforeInstantiation
 * 					         if (bean != null) {
 * 					         	bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *                           }
 *                  2）真正的创建一个bean实例：doCreateBean(beanName, mbdToUse, args)。整个过程同上3.4
 *
 *  AnnotationAwareAspectJAutoProxyCreator【InstantiationAwareBeanPostProcessor】的作用：
 *  1、每个Bean（MathCalculator）创建之前，调用 postProcessBeforeInstantiation
 *      关心MathCalculator和LogAspect的创建
 *      1）判断当前Bean是否在advisedBeans中（保存了所以需要增强的Bean）
 *      2）判断当前Bean是否是基础类型（isInfrastructureClass(Class<?> beanClass)，有Advice、Pointcut、Advisor、AopInfrastructureBean）
 *          或者是否是切面（isAspect(Class<?> clazz)），即有注解‘@Aspect’
 *      3）判读是否需要跳过（shouldSkip(beanClass, beanName)）
 *          1、获取候选的增强器（切面里的通知方法）【List<Advisor> candidateAdvisors】，类型为InstantiationModelAwarePointcutAdvisor
 *          判断每个增强器是否为AspectJPointcutAdvisor，若是则可以跳过。
 *          2、永远返回false
 *  2、创建Bean（MathCalculator）完成之后，调用 postProcessAfterInitialization
 *      包装如果需要的话：
 *      return wrapIfNecessary(bean, beanName, cacheKey)
 *      1）获取当前Bean的所有增强器（通知方法） Object[] specificInterceptors
 *          1、找到候选的增强器（找哪些通知方法是需要切入当前Bean方法的） findCandidateAdvisors()
 *          2、获取能在当前Bean使用的增强器 findAdvisorsThatCanApply(candidateAdvisors, beanClass, beanName)
 *          3、增强器排序 sortAdvisors(eligibleAdvisors)
 *      2）保存当前Bean在advisedBeans中
 *      3）如果当前Bean需要增强，创建当前Bean的代理对象 createProxy
 *          1、获取所有增强器（通知方法）
 *          2、保存到proxyFactory
 *          3、创建代理对象，spring自动觉定
 *              jdk动态代理 JdkDynamicAopProxy 或者
 *              cglib动态代理 ObjenesisCglibAopProxy
 *      4）给容器返回当前组件使用cglib增强了的代理对象
 *      5）以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程。
 *  3、目标方法执行（mathCalculator.div()）
 *      容器中保存了组件的代理对象（cglib增强后的），其中保存了详细信息：如增强器、目标方法等。
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

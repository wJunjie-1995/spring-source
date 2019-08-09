package config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import bean.autowire.BookDao;

/**
 * @author galileo  .
 * @date 2019-08-08:10:32
 *
 * 依赖注入(DI)：
 * 1、Spring注解：@Autowire
 *    1.优先按照类型去查找，当每个类型的Bean只有一个时,getBean(xxx.class)
 *    2.若每个类型的Bean存在多个时，则需要按照名字取匹配,getBean("xxx")
 *    3.解决Bean同名冲突
 *    		1）在@Autowire处使用@Qualifier(...)指定装配的Bean
 *    		2）在Bean处使用@Primary指定优先装配哪个Bean
 *    		注：Qualifier比Primary优先
 *    4.使用@Autowire的属性默认是一定要存在Bean可以装配的，否则会报错"NoSuchBean"
 *    	但是可以使用@Autowire(required = false)，当没有找到Bean装配时，将属性设为Null
 * 2、Java规范注解：@Resource（JSR250）或@Inject（JSR330）
 *    1.@Resource可以和@Autowire一样，默认按照组件名称进行装配
 *    	但是不能支持@Primary和require等功能
 *    2.@Inject需要导入javax.inject依赖，功能和@Autowire一样，
 *    	支持@Primary但是不支持require
 * 3、自定义组件使用spring底层Bean（如：ApplicationContext、BeanFactory等），
 * 		只需要自定义组件实现xxxAware即可：创建对象时，会根据接口对应的方法注入相关的组件。
 * 	注：Aware接口，回调函数风格（Callable-style method），
 * 		每一个xxxAware都一个对应的xxxProcessor（实现了BeanPostProcessor 后置处理器），
 * 		如ApplicationContextAware==》ApplicationContextAwareProcessor，
 *
 *
 */
@Configurable
@ComponentScan("bean.autowire")
@PropertySource(value = "classpath:/property.properties",encoding = "UTF-8")
public class AutowireConfig {

	@Bean("bookDao2")
	@Primary
	public BookDao bookDao(){
		BookDao bookDao = new BookDao();
		bookDao.setLabel("2");
		return bookDao;
	}

}

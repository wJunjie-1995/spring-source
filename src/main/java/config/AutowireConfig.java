package config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

import bean.autowire.BookDao;

/**
 * @author galileo  .
 * @date 2019-08-08:10:32
 *
 * 1.优先按照类型去查找，当每个类型的Bean只有一个时,getBean(xxx.class)
 * 2.若每个类型的Bean存在多个时，则需要按照名字取匹配,getBean("xxx")
 * 3.解决Bean同名冲突
 * 		1）在@Autowire处使用@Qualifier(...)指定装配的Bean
 * 		2）在Bean处使用@Primary指定优先装配哪个Bean
 * 		注：Qualifier比Primary优先
 * 4.使用@Autowire的属性默认是一定要存在Bean可以装配的，否则会报错"NoSuchBean"
 * 	但是可以使用@Autowire(required = false)，当没有找到Bean装配时，将属性设为Null
 */
@Configurable
@ComponentScan("bean.autowire")
public class AutowireConfig {

	@Bean("bookDao2")
	@Primary
	public BookDao bookDao(){
		BookDao bookDao = new BookDao();
		bookDao.setLabel("2");
		return bookDao;
	}

}

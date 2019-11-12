package bean.autowire;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * @author galileo  .
 * @date 2019-08-09:16:42
 */
@Component
public class Aware implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {
	private ApplicationContext applicationContext;

	@Override
	public void setBeanName(String name) {
		System.out.println("bean name:"+name);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("传入IOC："+applicationContext);
		this.applicationContext = applicationContext;
	}

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		String s = resolver.resolveStringValue("你好  ${os.name}");
		System.out.println("解析："+s);
	}
}

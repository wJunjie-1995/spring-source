package bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author galileo
 * @date 2019/8/1 17:17
 *
 * 顺序：
 * populateBean 为bean赋予属性值
 * initializeBean{
 * postProcessBeforeInitialization 初始化前
 * init 自定义初始化方法
 * postProcessAfterInitialization 初始化后
 * }
 */

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化之前"+beanName+"=>"+bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化之后"+beanName+"=>"+bean);
        return bean;
    }
}

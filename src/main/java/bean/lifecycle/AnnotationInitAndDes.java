package bean.lifecycle;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author galileo
 * @date 2019/8/1 17:08
 */
@Component
public class AnnotationInitAndDes {
    public AnnotationInitAndDes() {
        System.out.println("注解方式 构造器");
    }

    @PostConstruct
    public void init(){
        System.out.println("注解方式 PostConstruct（init）");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("注解方式 PreDestroy(destroy)");
    }
}

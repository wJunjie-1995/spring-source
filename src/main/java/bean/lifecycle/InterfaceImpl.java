package bean.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author galileo
 * @date 2019/8/1 17:00
 */
@Component
public class InterfaceImpl implements InitializingBean, DisposableBean {
    public void destroy() throws Exception {
        System.out.println("cat destroy");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("cat init ");
    }
}

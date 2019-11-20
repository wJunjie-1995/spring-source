package bean.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author galileo
 * @date 2019/8/1 17:00
 */

public class InterfaceInitAndDes implements InitializingBean, DisposableBean {
    public InterfaceInitAndDes() {
        System.out.println("接口方式 构造器");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("接口方式 destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("接口方式 init ");
    }
}

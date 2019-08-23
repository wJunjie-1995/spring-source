package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author galileo
 * @date 2019/8/23 16:18
 */
public class Company implements InvocationHandler {
    private Object factory;

    public void setFactory(Object factory) {
        this.factory = factory;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(factory.getClass().getClassLoader(),factory.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doSomethingBefore();
        Object ret = method.invoke(factory, args);
        doSomethingAfter();
        return ret;
    }

    private void doSomethingBefore(){
        System.out.println("前置增强");
    }

    private void doSomethingAfter(){
        System.out.println("后置增强");
    }
}

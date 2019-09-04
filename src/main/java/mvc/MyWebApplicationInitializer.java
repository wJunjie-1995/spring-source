package mvc;

import config.MainConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * @author galileo
 * @date 2019/9/4 10:17
 *
 * spring5使用零配置的原理：为什么Tomcat启动时会执行到 MyWebApplicationInitializer.onStart()方法？
 *
 *  Tomcat7之后的版本 实现了Servlet3.0规范，即（spring-web）classPath下的META-INF/services/javax.servlet.ServletContainerInitializer
 *      文件内的内容（org.springframework.web.SpringServletContainerInitializer），Tomcat启动时需要调用该类的onStart()方法
 *  而SpringServletContainerInitializer类上，有注解@HandlesTypes(WebApplicationInitializer.class)
 *      即会把WebApplicationInitializer的所有实现类打包成一个集合，
 *      传递给SpringServletContainerInitializer的onStart(Set<Class<?>> webAppInitializerClasses)
 *      然后会循环调用所有实现类的onStart()方法。
 *      for (WebApplicationInitializer initializer : initializers) {
 * 			initializer.onStartup(servletContext);
 *                }
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletCxt) {

        // Load Spring web application configuration
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(MainConfig.class);
        ac.refresh();

        // Create and register the DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/app/*");
    }
}

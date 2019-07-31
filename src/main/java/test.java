import bean.Person;
import config.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author galileo
 * @date 2019/7/31 15:47
 */
public class test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person.getName() );
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames){
            System.out.println(name);
        }
    }
}

package annotation;

/**
 * @author galileo
 * @date 2019/9/5 16:17
 */
public class BuildCommonSql {
    public static void buildCommonSql(Object object){
        Class<?> clazz = object.getClass();
        if (clazz.isAnnotationPresent(Entity.class)){
            Entity entity = clazz.getAnnotation(Entity.class);
            String value = entity.value();
            System.out.println(value);

        }else {
            System.out.println("wu");
        }
    }
}

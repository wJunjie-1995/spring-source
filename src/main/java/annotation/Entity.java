package annotation;

import org.aspectj.lang.annotation.DeclareAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author galileo
 * @date 2019/9/5 16:14
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {
    public String value() default "";
}

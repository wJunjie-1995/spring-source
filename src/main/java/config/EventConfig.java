package config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author galileo
 * @date 2019/11/12 10:53
 */
@Configurable
@ComponentScan("event")
public class EventConfig {
}

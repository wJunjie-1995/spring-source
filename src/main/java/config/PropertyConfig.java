package config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @author galileo  .
 * @date 2019-08-08:09:41
 *
 * PropertySource 读取外部配置文件中的K/V保存到运行环境。加载完配置文件后可以用${}读取其值。
 */

@Configurable
@PropertySource(value = "classpath:/property.properties",encoding = "UTF-8")
@ComponentScan("bean.property")
public class PropertyConfig {

}

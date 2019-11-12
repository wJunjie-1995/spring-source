package event;

import org.springframework.context.ApplicationEvent;

/**
 * @author galileo
 * @date 2019/11/12 10:45
 */
public class BlackListEvent extends ApplicationEvent {
    private String name;

    public BlackListEvent(Object source, String name) {
        super(source);
        this.name = name;
    }
}

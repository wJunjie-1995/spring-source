package event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author galileo
 * @date 2019/11/12 10:50
 */
@Component
public class BlackListListener {

    /**
     * 解释：@Async表示事件处理方使用异步处理方式（默认为同步）
     * 使用@Async注解需要注意：
     * 1.若发生Exception，将无法将Exception传播给调用方。需要用AsyncUncaughtExceptionHandler
     * 2.不能通过返回值的方式连续发布后续事件，只能注入ApplicationEventPublisher来重新发布
     *
     * @param event 事件
     */
    @EventListener
    @Async
    public void processBlackListEvent(BlackListEvent event) {
        System.out.println("process event :" + event.toString());
    }
}

package event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author galileo
 * @date 2019/11/12 10:50
 */
@Component
public class BlackListListener {

    @EventListener
    public void processBlackListEvent(BlackListEvent event){
        System.out.println("process event :"+event.toString());
    }
}

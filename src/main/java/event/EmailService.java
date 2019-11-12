package event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @author galileo
 * @date 2019/11/12 10:47
 */
@Service
public class EmailService implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void send(String name){
        if ("black".equals(name)){
            //被订阅这发布消息
            publisher.publishEvent(new BlackListEvent(this, name));
            return;
        }
        //send..
        System.out.println("send");
    }
}

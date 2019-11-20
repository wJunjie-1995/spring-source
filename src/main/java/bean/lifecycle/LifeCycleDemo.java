package bean.lifecycle;

import org.springframework.context.Lifecycle;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * @author galileo
 * @date 2019/11/19 17:26
 */
@Component
public class LifeCycleDemo implements SmartLifecycle {
    private volatile boolean running = false;
    @Override
    public void start() {
        System.out.println("LifeCycleDemo start");
        running = true;

    }
    @Override
    public void stop() {
        System.out.println("LifeCycleDemo stop");
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public boolean isAutoStartup() {
        return false;
    }

    @Override
    public void stop(Runnable runnable) {

    }

    @Override
    public int getPhase() {
        return 1;
    }
}

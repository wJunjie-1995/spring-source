package dependency;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author galileo
 * @date 2019/11/18 17:07
 */
@Component
@Scope("prototype")
public class Command {
    private int id = (int)(100*Math.random());;

    public void command(){
        System.out.println("command:"+id);
    }
}

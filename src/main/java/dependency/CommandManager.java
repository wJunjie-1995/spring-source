package dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author galileo
 * @date 2019/11/18 17:05
 */
@Component
@Scope("singleton")
public abstract class CommandManager {

//    private Command command;
//
//    public CommandManager(Command command) {
//        this.command = command;
//    }

    public void test(){
        Command command = createCommand();
        command.command();
    }

    @Lookup
     abstract Command createCommand();
}

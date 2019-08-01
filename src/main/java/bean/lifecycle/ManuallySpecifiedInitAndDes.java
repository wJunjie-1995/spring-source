package bean.lifecycle;

/**
 * @author galileo
 * @date 2019/8/1 15:40
 */
public class ManuallySpecifiedInitAndDes {
    public ManuallySpecifiedInitAndDes() {
        System.out.println("手动指定方式 构造器");
    }

    public void init(){
        System.out.println("手动指定方式 init");
    }

    public void destroy(){
        System.out.println("手动指定方式 destroy");
    }
}

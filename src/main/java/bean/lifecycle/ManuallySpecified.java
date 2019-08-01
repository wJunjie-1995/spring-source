package bean.lifecycle;

/**
 * @author galileo
 * @date 2019/8/1 15:40
 */
public class ManuallySpecified {
    public ManuallySpecified() {
        System.out.println("Car构造器");
    }

    public void init(){
        System.out.println("car init");
    }

    public void destroy(){
        System.out.println("car destroy");
    }
}

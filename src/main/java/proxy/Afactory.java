package proxy;

/**
 * @author galileo
 * @date 2019/8/23 16:15
 */
public class Afactory implements ManFactory{
    public void saleManTool(int size) {
        System.out.println("size为"+size+"的玩具准备好了");
    }
}

package proxy;

/**
 * @author galileo
 * @date 2019/8/23 16:15
 */
public class Bfactory implements WomanFactory{
    public void saleWomanTool(int length) {
        System.out.println("length为"+length+"的玩具准备好了");
    }
}

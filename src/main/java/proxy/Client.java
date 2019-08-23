package proxy;

/**
 * @author galileo
 * @date 2019/8/23 16:23
 */
public class Client {
    public static void main(String[] args) {
        ManFactory aFatory = new Afactory();
        WomanFactory bFactory = new Bfactory();

        Company company = new Company();
        company.setFactory(aFatory);
        ManFactory proxyInstance1 = (ManFactory) company.getProxyInstance();
        proxyInstance1.saleManTool(18);

        System.out.println("---------------");

        company.setFactory(bFactory);
        WomanFactory proxyInstance2 = (WomanFactory) company.getProxyInstance();
        proxyInstance2.saleWomanTool(20);
    }
}

package annotation;

/**
 * @author galileo
 * @date 2019/9/5 16:16
 */
@Entity("city")
public class City {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

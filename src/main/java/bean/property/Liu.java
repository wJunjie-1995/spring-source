package bean.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author galileo  .
 * @date 2019-08-08:09:46
 */
@Component
public class Liu {
	@Value("liu")
	private String name;
	@Value("22")
	private int age;
	@Value("${liu.nickName}")
	private String nickName;

	@Override
	public String toString() {
		return "person{" + "name='" + name + '\'' + ", age=" + age + ", nickName='" + nickName
				+ '\'' + '}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}

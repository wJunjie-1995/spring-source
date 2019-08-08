package bean.autowire;

import org.springframework.stereotype.Repository;

/**
 * @author galileo  .
 * @date 2019-08-08:10:32
 */
@Repository("bookDao")
public class BookDao {
	private String label = "1";

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "BookDao{" + "label='" + label + '\'' + '}';
	}
}

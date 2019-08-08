package bean.autowire;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author galileo  .
 * @date 2019-08-08:10:32
 */
@Service
public class BookService {

	@Qualifier("bookDao")
	private final BookDao bookDao;

	public BookService(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public String toString() {
		return "BookService{" + "bookDao=" + bookDao + '}';
	}
}

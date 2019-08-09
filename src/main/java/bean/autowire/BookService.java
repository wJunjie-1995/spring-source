package bean.autowire;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author galileo  .
 * @date 2019-08-08:10:32
 */
@Service
public class BookService {

//	@Qualifier("bookDao")
//	@Autowired

//	@Resource(name = "bookDao2")

	@Inject
	private BookDao bookDao;

	@Override
	public String toString() {
		return "BookService{" + "bookDao=" + bookDao + '}';
	}
}

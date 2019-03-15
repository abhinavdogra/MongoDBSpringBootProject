package abhi.project.mdb.Query;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QueryDslPredicateExecutor<T> {

	  T findOne(Predicate predicate);

	  List<T> findAll(Predicate predicate);

	  Page<T> findAll(Predicate predicate, Pageable pageable);

	  Long count(Predicate predicate);
}

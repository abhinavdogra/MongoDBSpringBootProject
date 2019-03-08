package abhi.project.mdb.MongoDAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import abhi.project.mdb.MovieEntity.Shows;

@Component
public interface IShowRepository extends MongoRepository<Shows, Object> {

	public List<Shows> findByShowName(String showName);


}

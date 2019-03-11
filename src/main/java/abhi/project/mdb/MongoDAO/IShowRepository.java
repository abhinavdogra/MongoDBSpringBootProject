package abhi.project.mdb.MongoDAO;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import abhi.project.mdb.MovieEntity.Movie;
import abhi.project.mdb.MovieEntity.Shows;

@Component
public interface IShowRepository extends MongoRepository<Shows, Object>{

	public List<Shows> findByShowID(int showId);
	public List<Shows> findByShowName(String showName);
	public List<Shows> findByDateOfRelease(Date dateOfRelease);
	public List<Shows> findByCast_ActorsIn(String actor);
	public List<Shows> findByCast_ProducersIn(String producer);
	public List<Shows> findByCast_DirectorsIn(String actor);
}

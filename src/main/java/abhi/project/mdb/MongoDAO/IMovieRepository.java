package abhi.project.mdb.MongoDAO;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import abhi.project.mdb.MovieEntity.Movie;

@Component
public interface IMovieRepository extends MongoRepository<Movie, Object> {

	// "movieId/showId" or "movieName/showName" or "actor" or "producer" "director"
	// or "dataOfRelease"
	public List<Movie> findByMovieId(int movieId);

	public List<Movie> findByMovieName(String movieName);

	public List<Movie> findByDateOfRelease(Date dateOfRelease);

	public List<Movie> findByCast_ActorsIn(String actor);

	public List<Movie> findByCast_ProducersIn(String producer);

	public List<Movie> findByCast_DirectorsIn(String actor);
}

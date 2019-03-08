package abhi.project.mdb.MongoDAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import abhi.project.mdb.MovieEntity.Movie;
import abhi.project.mdb.MovieEntity.Shows;
import abhi.project.mdb.MovieModal.SearchCriteria;

@Component
public interface IMovieRepository extends MongoRepository<Movie,Object> {

	public List<Shows> findByMovieName(String movieName);

}

package abhi.project.mdb.MongoDAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import abhi.project.mdb.MovieEntity.Comments;

@Component
public interface ICommentsRepository extends MongoRepository<Comments, Object> {

	public List<Comments> findByMovieId(int movieId);

	public List<Comments> findByShowID(int showID);

}

package abhi.project.mdb.MongoDAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import abhi.project.mdb.MovieEntity.Comments;

public interface ICommentsRepository extends MongoRepository<Comments, String> {

	public Comments findByMovieID(String movieID);

	public Comments findByShowID(String showID);

}

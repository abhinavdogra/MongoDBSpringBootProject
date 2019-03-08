package abhi.project.mdb.MovieModal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import abhi.project.mdb.MongoDAO.DBOperations;
import abhi.project.mdb.MovieEntity.Comments;
import abhi.project.mdb.MovieEntity.Movie;
import abhi.project.mdb.main.JSONParserUtility;

@Component
public class MovieImpl implements IMovies {

	@Autowired
	DBOperations dbOperations;

	@Override
	public Object addMovie(Movie movie) {
		Movie createdMovie = dbOperations.saveMovie(movie);
		return createdMovie;//JSONParserUtility.createJSONObject(createdMovie);
	}

	@Override
	public Object updateMovie(Movie movie) {
		return null;
	}

	@Override
	public void addComments(Comments comments) {

	}

	@Override
	public Object search(SearchCriteria search) {
		// TODO Auto-generated method stub
		return null;
	}

}

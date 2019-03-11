package abhi.project.mdb.MovieModal;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import abhi.project.mdb.MDBException.MDBException;
import abhi.project.mdb.MongoDAO.DBOperations;
import abhi.project.mdb.MovieEntity.Comments;
import abhi.project.mdb.MovieEntity.Movie;
import abhi.project.mdb.Validation.MDBValidation;

@Component
public class MovieImpl implements IMovies {

	@Autowired
	DBOperations dbOperations;

	@Autowired
	MDBValidation validation;

	@Override
	public Object addMovie(Movie movie) throws MDBException {
		validation.validateMovieDetails(movie);
		Movie createdMovie = dbOperations.saveMovie(movie);
		return createdMovie;
	}

	@Override
	public Object updateMovie(Movie movie) throws MDBException {
		validation.validateMovieDetails(movie);
		Movie updatedMovie = dbOperations.updateMovie(movie);
		return updatedMovie;
	}

	@Override
	public Object addComments(Comments comments) {
		return dbOperations.saveComments(comments, true);
	}

	@Override
	public Object search(SearchCriteria search) throws JSONException {
		return dbOperations.search(search, true);
	}

}

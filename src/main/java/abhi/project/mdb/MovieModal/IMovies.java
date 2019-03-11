package abhi.project.mdb.MovieModal;

import abhi.project.mdb.MDBException.MDBException;
import abhi.project.mdb.MovieEntity.Movie;

public interface IMovies extends IMovieDB {

	public Object addMovie(Movie movie) throws MDBException;

	public Object updateMovie(Movie movie) throws MDBException;

}

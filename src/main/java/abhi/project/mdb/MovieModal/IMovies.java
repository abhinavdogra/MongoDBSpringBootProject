package abhi.project.mdb.MovieModal;

import org.springframework.stereotype.Component;

import abhi.project.mdb.MovieEntity.Movie;

public interface IMovies extends IMovieDB {

	public Object addMovie(Movie movie);

	public Object updateMovie(Movie movie);

}

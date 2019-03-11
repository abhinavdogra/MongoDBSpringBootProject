package abhi.project.mdb.MongoDAO;

import abhi.project.mdb.MovieEntity.Comments;
import abhi.project.mdb.MovieEntity.Movie;
import abhi.project.mdb.MovieEntity.Shows;

public interface IDBOperations {

	public Movie saveMovie(Movie movie);

	public Shows saveShows(Shows shows);

	public Movie updateMovie(Movie movie);
	
	public Shows updateShow(Shows shows);

	public Object saveComments(Comments comments, boolean isMovie);

}

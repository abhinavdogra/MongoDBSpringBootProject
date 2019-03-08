package abhi.project.mdb.MongoDAO;

import abhi.project.mdb.MovieEntity.Actor;
import abhi.project.mdb.MovieEntity.Cast;
import abhi.project.mdb.MovieEntity.Channels;
import abhi.project.mdb.MovieEntity.Comments;
import abhi.project.mdb.MovieEntity.Movie;
import abhi.project.mdb.MovieEntity.Shows;

public interface IDBOperations {

	public Movie saveMovie(Movie movie);

	public Shows saveShows(Shows shows);

	public Comments saveComments(Comments movie);

	public Cast saveCast(Cast cast);

	public Actor saveActors(Actor actor);

	public Channels saveChannels(Channels channels);

}

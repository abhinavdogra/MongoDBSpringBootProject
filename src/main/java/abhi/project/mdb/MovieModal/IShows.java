package abhi.project.mdb.MovieModal;

import abhi.project.mdb.MovieEntity.Shows;

public interface IShows extends IMovieDB {

	public Object addShow(Shows show);

	public Object updateShow(Shows show);
}

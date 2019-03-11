package abhi.project.mdb.MovieModal;

import abhi.project.mdb.MDBException.MDBException;
import abhi.project.mdb.MovieEntity.Shows;

public interface IShows extends IMovieDB {

	public Object addShow(Shows show) throws MDBException;

	public Object updateShow(Shows show) throws MDBException;
}

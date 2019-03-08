package abhi.project.mdb.MovieModal;

import abhi.project.mdb.MovieEntity.Comments;

public interface IMovieDB {
	//Common to both
	public void addComments(Comments comments);
	public Object search(SearchCriteria search);
}

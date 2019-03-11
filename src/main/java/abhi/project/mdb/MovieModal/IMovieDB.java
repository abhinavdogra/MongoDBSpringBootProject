package abhi.project.mdb.MovieModal;

import org.codehaus.jettison.json.JSONException;

import abhi.project.mdb.MovieEntity.Comments;

public interface IMovieDB {
	//Common to both
	public Object addComments(Comments comments);
	public Object search(SearchCriteria search) throws JSONException;
}

package abhi.project.mdb.MovieModal;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jettison.json.JSONException;

import abhi.project.mdb.MovieEntity.Comments;

public interface IMovieDB {
	// Common to both
	public Object addComments(Comments comments);

	public List<? extends Object> search(SearchCriteria search)
			throws JSONException, JsonGenerationException, JsonMappingException, IOException;
}

package abhi.project.mdb.MovieModal;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import abhi.project.mdb.MDBException.MDBException;
import abhi.project.mdb.MongoDAO.DBOperations;
import abhi.project.mdb.MovieEntity.Comments;
import abhi.project.mdb.MovieEntity.Shows;
import abhi.project.mdb.Validation.MDBValidation;

@Component
public class ShowsImpl implements IShows {

	@Autowired
	DBOperations dbOperations;
	
	@Autowired
	MDBValidation validation;

	@Override
	public Object addShow(Shows show) throws MDBException {
		validation.validateShowDetails(show);
		Shows createdShow = dbOperations.saveShows(show);
		return createdShow;// JSONParserUtility.createJSONObject(createdMovie);
	}

	@Override
	public Object updateShow(Shows show) throws MDBException {
		Shows updatedShow = dbOperations.updateShow(show);
		return updatedShow;
	}

	@Override
	public Object addComments(Comments comments) {
		return dbOperations.saveComments(comments, false);
	}

	@Override
	public List<Shows> search(SearchCriteria search) throws JSONException, JsonGenerationException, JsonMappingException, IOException {
		return (List<Shows>) dbOperations.search(search, false);
	}

}

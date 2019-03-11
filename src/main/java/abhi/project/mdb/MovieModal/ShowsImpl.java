package abhi.project.mdb.MovieModal;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import abhi.project.mdb.MDBException.MDBException;
import abhi.project.mdb.MongoDAO.DBOperations;
import abhi.project.mdb.MovieEntity.Comments;
import abhi.project.mdb.MovieEntity.Movie;
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
		validation.validateShowDetails(show);
		Shows updatedShow = dbOperations.updateShow(show);
		return updatedShow;
	}

	@Override
	public Object addComments(Comments comments) {
		return dbOperations.saveComments(comments, false);
	}

	@Override
	public Object search(SearchCriteria search) throws JSONException {
		return dbOperations.search(search, false);
	}

}

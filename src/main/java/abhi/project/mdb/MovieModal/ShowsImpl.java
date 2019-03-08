package abhi.project.mdb.MovieModal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import abhi.project.mdb.MongoDAO.DBOperations;
import abhi.project.mdb.MovieEntity.Comments;
import abhi.project.mdb.MovieEntity.Shows;

@Component
public class ShowsImpl implements IShows {

	@Autowired
	DBOperations dbOperations;

	@Override
	public Object addShow(Shows show) {
		Shows createdShow = dbOperations.saveShows(show);
		return createdShow;// JSONParserUtility.createJSONObject(createdMovie);
	}

	@Override
	public Object updateShow(Shows show) {
		return null;
	}

	@Override
	public void addComments(Comments comments) {

	}

	@Override
	public Object search(SearchCriteria search) {
		// TODO Auto-generated method stub
		return null;
	}

}

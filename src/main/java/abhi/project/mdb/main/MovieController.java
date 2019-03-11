package abhi.project.mdb.main;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import abhi.project.mdb.MDBException.MDBException;
import abhi.project.mdb.MovieEntity.Comments;
import abhi.project.mdb.MovieEntity.Movie;
import abhi.project.mdb.MovieEntity.Shows;
import abhi.project.mdb.MovieModal.MovieImpl;
import abhi.project.mdb.MovieModal.SearchCriteria;
import abhi.project.mdb.MovieModal.ShowsImpl;

@RestController
@RequestMapping("/application/mdb")
public class MovieController {

	@Autowired
	MovieImpl movies;

	@Autowired
	ShowsImpl shows;

	@PostMapping(value = "add/movie", consumes = "application/json", produces = "application/json")
	public Object addMovie(@RequestBody Movie movie) throws MDBException {
		Object createdMovie = movies.addMovie(movie);
		new ResponseEntity<>("movie created with ID", HttpStatus.OK);
		return createdMovie;

	}

	@PostMapping(value = "add/show", consumes = "application/json", produces = "application/json")
	public Object addShow(@RequestBody Shows show) throws MDBException {
		Object createdShow = shows.addShow(show);
		return createdShow;

	}

	@PutMapping(value = "update/movie", consumes = "application/json", produces = "application/json")
	public Object updateMovie(@RequestBody Movie movie) throws MDBException {
		Object updatedMovie = movies.updateMovie(movie);
		return updatedMovie;

	}

	@PutMapping(value = "update/show", consumes = "application/json", produces = "application/json")
	public Object updateShow(@RequestBody Shows show) throws MDBException {
		Object updatedShow = shows.updateShow(show);
		return updatedShow;

	}

	@PostMapping(value = "movies/search", consumes = "application/json", produces = "application/json")
	public Object searchMovie(@RequestBody SearchCriteria search) throws JSONException {
		Object seacrhResult = movies.search(search);
		JSONParserUtility.createJSONObjectForMovie(seacrhResult);
		return seacrhResult;
	}

	@PostMapping(value = "shows/search", consumes = "application/json", produces = "application/json")
	public Object searchShows(@RequestBody SearchCriteria search) throws JSONException {
		Object seacrhResult = shows.search(search);
		return seacrhResult;
	}

	@PostMapping(value = "comments", consumes = "application/json", produces = "application/json")
	public Object addComments(@RequestBody Comments comments) throws MDBException {

		if (comments.getMovieID() > 0) {
			return movies.addComments(comments);
		} else if (comments.getShowID() > 0) {
			return shows.addComments(comments);
		} else
			throw new MDBException("Comments doesn't have a vaid MovieID/ShowID");
	}

}

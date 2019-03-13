package abhi.project.mdb.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	public @ResponseBody ResponseEntity<?> addMovie(@RequestBody Movie movie) {
		Object createdMovie = null;
		try {
			createdMovie = movies.addMovie(movie);
		} catch (MDBException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		HttpHeaders head = new HttpHeaders();
		head.add("Status", "Movie created sucessfully.");
		return new ResponseEntity<Object>(createdMovie, head, HttpStatus.OK);

	}

	@PostMapping(value = "add/show", consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<?> addShow(@RequestBody Shows show) {
		Object createdShow = null;

		try {
			createdShow = shows.addShow(show);
		} catch (MDBException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		HttpHeaders head = new HttpHeaders();
		head.add("Status", "Show created sucessfully.");
		return new ResponseEntity<Object>(createdShow, head, HttpStatus.OK);

	}

	@PutMapping(value = "update/movie", consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<?> updateMovie(@RequestBody Movie movie) {
		Object updatedMovie = null;
		try {
			updatedMovie = movies.updateMovie(movie);
		} catch (MDBException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		HttpHeaders head = new HttpHeaders();
		head.add("Status", "Movie updated sucessfully.");
		return new ResponseEntity<Object>(updatedMovie, head, HttpStatus.OK);

	}

	@PutMapping(value = "update/show", consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<?> updateShow(@RequestBody Shows show) {
		Object updatedShow = null;
		try {
			updatedShow = shows.updateShow(show);
		} catch (MDBException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		HttpHeaders head = new HttpHeaders();
		head.add("Status", "Show updated sucessfully.");
		return new ResponseEntity<Object>(updatedShow, head, HttpStatus.OK);

	}

	@PostMapping(value = "movies/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> searchMovie(@RequestBody SearchCriteria search) throws JSONException {
		List<Movie> list = new ArrayList<>();
		try {
			list = movies.search(search);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}

	@PostMapping(value = "shows/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> searchShows(@RequestBody SearchCriteria search) throws JSONException {
		List<Shows> list = new ArrayList<>();
		try {
			list = shows.search(search);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}

	@PostMapping(value = "comments", consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<?> addComments(@RequestBody Comments comments) {
		Object comment = null;
		if (comments.getMovieId() > 0) {
			comment = movies.addComments(comments);
		} else if (comments.getShowID() > 0) {
			comment = shows.addComments(comments);
		} else
			return new ResponseEntity<Object>("Comments doesn't have a valid MovieID/ShowID", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(comment, HttpStatus.OK);
	}

}

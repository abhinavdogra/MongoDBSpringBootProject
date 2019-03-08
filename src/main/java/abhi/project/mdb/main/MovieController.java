package abhi.project.mdb.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import abhi.project.mdb.MovieEntity.Comments;
import abhi.project.mdb.MovieEntity.Movie;
import abhi.project.mdb.MovieEntity.Shows;
import abhi.project.mdb.MovieModal.IMovieDB;
import abhi.project.mdb.MovieModal.IMovies;
import abhi.project.mdb.MovieModal.IShows;
import abhi.project.mdb.MovieModal.MovieImpl;
import abhi.project.mdb.MovieModal.ShowsImpl;

@RestController
@RequestMapping("/application/movies")
public class MovieController {

	@Autowired
	MovieImpl movies;
	
	@PostMapping(value = "add/movie", consumes = "application/json", produces = "application/json")
	public Object addMovie(@RequestBody Movie movie) {
		Object createdMovie = movies.addMovie(movie);
		return createdMovie;

	}

	@PostMapping(value = "add/show", consumes = "application/json", produces = "application/json")
	public Object addShow(@RequestBody Shows show) {
		IShows shows = new ShowsImpl();
		Object createdShow = shows.addShow(show);
		return createdShow;

	}

	@PutMapping(value = "update/movie", consumes = "application/json", produces = "application/json")
	public Object updateMovie(@RequestBody Movie movie) {
		IMovies movies = new MovieImpl();
		Object updatedMovie = movies.updateMovie(movie);
		return null;

	}

	@PutMapping(value = "update/show", consumes = "application/json", produces = "application/json")
	public Object updateShow(@RequestBody Shows show) {
		IShows shows = new ShowsImpl();
		Object updatedShow = shows.updateShow(show);
		return null;

	}

	@RequestMapping(value = "searchMovie", consumes = "application/json", produces = "application/json")
	public Object searchMovie(HttpEntity<String> httpEntity) {
		return null;
	}

	@PostMapping(value = "comments", consumes = "application/json", produces = "application/json")
	public void addComments(@RequestBody Comments comments) {
		IMovieDB movieDB;

		if (comments.getMovieID() != null) {
			movieDB = new MovieImpl();
			movieDB.addComments(comments);
		} else if (comments.getShowID() != null) {
			movieDB = new ShowsImpl();
			movieDB.addComments(comments);
		}
	}

}

package abhi.project.mdb.MongoDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import abhi.project.mdb.MDBException.MDBException;
import abhi.project.mdb.MovieEntity.Comments;
import abhi.project.mdb.MovieEntity.Movie;
import abhi.project.mdb.MovieEntity.Shows;
import abhi.project.mdb.MovieModal.SearchCriteria;
import abhi.project.mdb.Validation.MDBValidation;

@Component
public class DBOperations implements IDBOperations {

	@Autowired
	IMovieRepository movieRepo;

	@Autowired
	IShowRepository showRepo;

	@Autowired
	MDBValidation validation;

	@Autowired
	ICommentsRepository commentsRepo;

	@Override
	public Movie saveMovie(Movie movie) {
		int lastMovieID = getMovieIdLast();
		List<Movie> movies = getMovieList();
		try {
			validation.validateMovieName(movie.getMovieName(), movies);
		} catch (MDBException e) {
			e.printStackTrace();
		}
		movie.setMovieId(lastMovieID + 1);
		return movieRepo.insert(movie);
	}

	@Override
	public Movie updateMovie(Movie movie) {
		List<Movie> movies = getMovieList();
		try {
			validation.validateMovieByID(movie.getMovieId(), movies);
		} catch (MDBException e) {
			e.printStackTrace();
		}

		List<Movie> moviesList = movieRepo.findByMovieId(movie.getMovieId());
		Movie savedMovie = updateMovie(movie, moviesList);
		return movieRepo.save(savedMovie);
	}

	private Movie updateMovie(Movie movie, List<Movie> moviesList) {
		Movie saved = moviesList.get(0);

		if (movie.getCast() != null) {
			saved.setCast(movie.getCast());
		}

		if (movie.getTheaters() != null) {
			saved.setTheaters(movie.getTheaters());
		}

		if (movie.getDateOfRelease() != null) {
			saved.setDateOfRelease(movie.getDateOfRelease());
		}

		if (movie.getDuration() > 0.0) {
			saved.setDuration(movie.getDuration());
		}

		if (movie.getRating() > 0) {
			saved.setRating(movie.getRating());
		}

		if (movie.getMovieDescription() != null) {
			saved.setMovieDescription(movie.getMovieDescription());
		}

		if (movie.getMovieName() != null) {
			saved.setMovieName(movie.getMovieName());
		}

		return saved;
	}

	@Override
	public Shows updateShow(Shows shows) {
		List<Shows> showsList = getShowsList();
		try {
			validation.validateShowByID(shows.getShowID(), showsList);
		} catch (MDBException e) {
			e.printStackTrace();
		}

		List<Shows> show = showRepo.findByShowID(shows.getShowID());
		Shows savedShow = updateShows(shows, show);
		return showRepo.save(savedShow);
	}

	private Shows updateShows(Shows updatedShow, List<Shows> savedShow) {
		Shows saved = savedShow.get(0);

		if (updatedShow.getCast() != null) {
			saved.setCast(updatedShow.getCast());
		}

		if (updatedShow.getChannels() != null) {
			saved.setChannels(updatedShow.getChannels());
		}

		if (updatedShow.getDateOfRelease() != null) {
			saved.setDateOfRelease(updatedShow.getDateOfRelease());
		}

		if (updatedShow.getDuration() > 0.0) {
			saved.setDuration(updatedShow.getDuration());
		}

		if (updatedShow.getRating() > 0) {
			saved.setRating(updatedShow.getRating());
		}

		if (updatedShow.getShowDescription() != null) {
			saved.setShowDescription(updatedShow.getShowDescription());
		}

		if (updatedShow.getShowName() != null) {
			saved.setShowName(updatedShow.getShowName());
		}

		return saved;
	}

	@Override
	public Shows saveShows(Shows shows) {
		int lastShowID = getShowIdLast();
		List<Shows> showsList = getShowsList();
		try {
			validation.validateShowName(shows.getShowName(), showsList);
		} catch (MDBException e) {
			e.printStackTrace();
		}
		shows.setShowID(lastShowID + 1);
		return showRepo.insert(shows);
	}

	@Override
	public Object saveComments(Comments comments, boolean isMovie) {
		if (isMovie) {
			List<Movie> movies = getMovieList();
			try {
				validation.validateMovieByID(comments.getMovieId(), movies);
			} catch (MDBException e) {
				e.printStackTrace();
			}
		} else {
			List<Shows> showsList = getShowsList();
			try {
				validation.validateShowByID(comments.getShowID(), showsList);
			} catch (MDBException e) {
				e.printStackTrace();
			}
		}

		return commentsRepo.insert(comments);
	}

	private Integer getMovieIdLast() {
		List<Movie> moviesList = getMovieList();
		if (moviesList.isEmpty())
			return 0;
		List<Integer> movieIds = moviesList.stream().map(i -> i.getMovieId()).collect(Collectors.toList());
		return movieIds.stream().max(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2)
					return 1;
				else if (o1 < o2)
					return -1;
				return 0;
			}
		}).get();
	}

	private List<Movie> getMovieList() {
		return movieRepo.findAll();
	}

	private int getShowIdLast() {
		List<Shows> showsList = getShowsList();
		if (showsList.isEmpty())
			return 0;
		List<Integer> showIds = showsList.stream().map(i -> i.getShowID()).collect(Collectors.toList());
		return showIds.stream().max(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2)
					return 1;
				else if (o1 < o2)
					return -1;
				return 0;
			}
		}).get();
	}

	private List<Shows> getShowsList() {
		return showRepo.findAll();
	}

	public  List<?> search(SearchCriteria search, boolean isMovie)
			throws JSONException, JsonGenerationException, JsonMappingException, IOException {
		if (isMovie)
			return searchMovieCollection(search);
		else
			return searchShowCollection(search);
	}

	private  List<List<Shows>> searchShowCollection(SearchCriteria search)
			throws JSONException, JsonGenerationException, JsonMappingException, IOException {
//		JSONObject obj = new JSONObject();
		List<List<Shows>> shows = new ArrayList<>();
		if (search.getShowId() > 0) {
			shows.add(showRepo.findByShowID(search.getShowId()));
//			JSONArray arr = JSONParserUtility.createJSONObjectForShows(shows);
//			obj.put("showID", arr);
		} else if (null != search.getShowName() && !search.getShowName().equals("")) {
			shows.add(showRepo.findByShowName(search.getShowName()));
//			JSONArray arr = JSONParserUtility.createJSONObjectForShows(shows);
//			obj.put("showName", arr);
		} else if (null != search.getActor() && !search.getActor().equals("")) {
			shows.add(showRepo.findByCast_ActorsIn(search.getActor()));
//			JSONArray arr = JSONParserUtility.createJSONObjectForShows(shows);
//			obj.put("actor", arr);
		} else if (null != search.getDirector() && !search.getDirector().equals("")) {
			shows.add(showRepo.findByCast_DirectorsIn(search.getDirector()));
//			JSONArray arr = JSONParserUtility.createJSONObjectForShows(shows);
//			obj.put("director", arr);
		} else if (null != search.getProducer() && !search.getProducer().equals("")) {
			shows.add(showRepo.findByCast_ProducersIn(search.getProducer()));
//			JSONArray arr = JSONParserUtility.createJSONObjectForShows(shows);
//			obj.put("producers", arr);
		} else if (null != search.getDateOfRelease()) {
			shows.add(showRepo.findByDateOfRelease(search.getDateOfRelease()));
//			JSONArray arr = JSONParserUtility.createJSONObjectForShows(shows);
//			obj.put("date", arr);
		}

		return shows;
	}

	private List<List<Movie>> searchMovieCollection(SearchCriteria search)
			throws JSONException, JsonGenerationException, JsonMappingException, IOException {
	//	JSONObject obj = new JSONObject();
		List<List<Movie>> movies = new ArrayList<>();
		if (search.getMovieId() > 0) {
			movies.add(movieRepo.findByMovieId(search.getMovieId()));
//			JSONArray arr = JSONParserUtility.createJSONObjectForMovie(movies);
//			obj.put("movieID", arr);
		} else if (null != search.getMovieName() && !search.getMovieName().equals("")) {
			movies.add(movieRepo.findByMovieName(search.getMovieName()));
//			JSONArray arr = JSONParserUtility.createJSONObjectForMovie(movies);
//			obj.put("movieNames", arr);
		} else if (null != search.getActor() && !search.getActor().equals("")) {
			movies.add(movieRepo.findByCast_ActorsIn(search.getActor()));
//			JSONArray arr = JSONParserUtility.createJSONObjectForMovie(movies);
//			obj.put("actor", arr);
		} else if (null != search.getDirector() && !search.getDirector().equals("")) {
			movies.add(movieRepo.findByCast_DirectorsIn(search.getDirector()));
//			JSONArray arr = JSONParserUtility.createJSONObjectForMovie(movies);
//			obj.put("director", arr);
		} else if (null != search.getProducer() && !search.getProducer().equals("")) {
			movies.add(movieRepo.findByCast_ProducersIn(search.getProducer()));
//			JSONArray arr = JSONParserUtility.createJSONObjectForMovie(movies);
//			obj.put("producers", arr);
		} else if (null != search.getDateOfRelease()) {
			movies.add(movieRepo.findByDateOfRelease(search.getDateOfRelease()));
//			JSONArray arr = JSONParserUtility.createJSONObjectForMovie(movies);
//			obj.put("date", arr);
		}

		return movies;
	}

}

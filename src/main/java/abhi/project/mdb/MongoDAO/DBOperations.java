package abhi.project.mdb.MongoDAO;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
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
			validation.validateMovieDetails(movie);
		} catch (MDBException e) {
			e.printStackTrace();
		}

		return movieRepo.save(movie);
	}

	@Override
	public Shows updateShow(Shows shows) {
		List<Shows> showsList = getShowsList();
		try {
			validation.validateShowByID(shows.getShowID(), showsList);
			validation.validateShowDetails(shows);
		} catch (MDBException e) {
			e.printStackTrace();
		}

		return showRepo.save(shows);
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
				validation.validateMovieByID(comments.getMovieID(), movies);
			} catch (MDBException e) {
				// TODO Auto-generated catch block
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
				if (o1 > 02)
					return 1;
				else if (o2 < o1)
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
				if (o1 > 02)
					return 1;
				else if (o2 < o1)
					return -1;
				return 0;
			}
		}).get();
	}

	private List<Shows> getShowsList() {
		return showRepo.findAll();
	}

	public Object search(SearchCriteria search, boolean isMovie) throws JSONException {
		if (isMovie)
			return searchMovieCollection(search);
		else
			return searchShowCollection(search);
	}

	private Object searchShowCollection(SearchCriteria search) throws JSONException {
		JSONObject obj = new JSONObject();
		List<Shows> shows;
		if (search.getMovieId() > 0) {
			shows = showRepo.findByShowID(search.getShowId());
			obj.put("showID", shows);
		} else if (null != search.getMovieName() || !search.getMovieName().equals("")) {
			shows = showRepo.findByShowName(search.getShowName());
			obj.put("showName", shows);
		}else if (null != search.getActor() && !search.getActor().equals("")) {
			shows = showRepo.findByCast_ActorsIn(search.getActor());
			obj.put("actor", shows);
		} else if (null != search.getDirector() && !search.getDirector().equals("")) {
			shows = showRepo.findByCast_DirectorsIn(search.getDirector());
			obj.put("director", shows);
		} else if (null != search.getProducer() && !search.getProducer().equals("")) {
			shows = showRepo.findByCast_ProducersIn(search.getProducer());
			obj.put("producers", shows);
		} else if (null != search.getDateOfRelease()) {
			shows = showRepo.findByDateOfRelease(search.getDateOfRelease());
			obj.put("date", shows);
		}

		return obj;
	}

	private Object searchMovieCollection(SearchCriteria search) throws JSONException {
		JSONObject obj = new JSONObject();
		List<Movie> movies;
		if (search.getMovieId() > 0) {
			movies = movieRepo.findByMovieId(search.getMovieId());
			obj.put("movieID", movies);
		} else if (null != search.getMovieName() && !search.getMovieName().equals("")) {
			movies = movieRepo.findByMovieName(search.getMovieName());
			obj.put("movieNames", movies);
		}else if (null != search.getActor() && !search.getActor().equals("")) {
			movies = movieRepo.findByCast_ActorsIn(search.getActor());
			obj.put("actor", movies);
		} else if (null != search.getDirector() && !search.getDirector().equals("")) {
			movies = movieRepo.findByCast_DirectorsIn(search.getDirector());
			obj.put("director", movies);
		} else if (null != search.getProducer() && !search.getProducer().equals("")) {
			movies = movieRepo.findByCast_ProducersIn(search.getProducer());
			obj.put("producers", movies);
		} else if (null != search.getDateOfRelease()) {
			movies = movieRepo.findByDateOfRelease(search.getDateOfRelease());
			obj.put("date", movies);
		}

		return obj;
	}

}

package abhi.project.mdb.MongoDAO;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import abhi.project.mdb.MovieEntity.Actor;
import abhi.project.mdb.MovieEntity.Cast;
import abhi.project.mdb.MovieEntity.Channels;
import abhi.project.mdb.MovieEntity.Comments;
import abhi.project.mdb.MovieEntity.Movie;
import abhi.project.mdb.MovieEntity.Shows;

@Component
public class DBOperations implements IDBOperations {

	@Autowired
	IMovieRepository movieRepo;

	@Autowired
	IShowRepository showRepo;

	@Override
	public Movie saveMovie(Movie movie) {
		int lastMovieID = getMovieIdLast();
		movie.setMovieId(lastMovieID + 1);
		return movieRepo.save(movie);
	}

	@Override
	public Shows saveShows(Shows shows) {
		int lastShowID = getShowIdLast();
		shows.setShowID(lastShowID + 1);
		return showRepo.save(shows);
	}

	@Override
	public Comments saveComments(Comments movie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cast saveCast(Cast cast) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Actor saveActors(Actor actor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Channels saveChannels(Channels channels) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getMovieIdLast() {
		List<Movie> moviesList = movieRepo.findAll();
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

	private int getShowIdLast() {
		List<Shows> showsList = showRepo.findAll();
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

}

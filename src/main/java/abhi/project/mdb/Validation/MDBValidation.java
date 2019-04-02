package abhi.project.mdb.Validation;

import java.util.List;

import org.springframework.stereotype.Component;

import abhi.project.mdb.MDBException.MDBException;
import abhi.project.mdb.MovieEntity.Movie;
import abhi.project.mdb.MovieEntity.Shows;

@Component
public class MDBValidation {

	public void validateMovieName(String movieName, List<Movie> movieList) throws MDBException {
		for (Movie m : movieList) {
			if (m.getMovieName().equals(movieName))
				throw new MDBException("Movie already exist");
		}
	}

	public void validateShowName(String showName, List<Shows> showsList) throws MDBException {
		for (Shows s : showsList) {
			if (s.getShowName().equals(showName))
				throw new MDBException("Show already exist");
		}
	}

	public void validateMovieDetails(Movie movie) throws MDBException {
		StringBuffer sb = new StringBuffer();
		boolean isMovieValid = true;
		if (null == movie.getMovieName() || movie.getMovieName().equals("")) {
			sb.append("Movie name is missing.");
			sb.append(" ");
			isMovieValid = false;
		}
		if (movie.getDuration() <= 0.0) {
			sb.append("Movie is missing duration.");
			sb.append(" ");
			isMovieValid = false;
		}
		if (null == movie.getDateOfRelease() || movie.getDateOfRelease().equals("")) {
			sb.append("Movie name is date of release.");
			isMovieValid = false;
		}

		if (!isMovieValid)
			throw new MDBException("Movie is not valid. Missing fields: " + sb.toString().trim());

	}

	public void validateShowDetails(Shows shows) throws MDBException {
		StringBuffer sb = new StringBuffer();
		boolean isShowValid = true;
		if (null == shows.getShowName() || shows.getShowName().equals("")) {
			sb.append("Show name is missing.");
			sb.append(" ");
			isShowValid = false;
		}
		if (shows.getDuration() <= 0) {
			sb.append("Show is missing duration.");
			sb.append(" ");
			isShowValid = false;
		}
		if (null == shows.getDateOfRelease() || shows.getDateOfRelease().equals("")) {
			sb.append("Show name is date of release.");
			isShowValid = false;
		}

		if (!isShowValid)
			throw new MDBException("Show is not valid. Missing fields: " + sb.toString().trim());

	}

	public void validateMovieByID(int movieId, List<Movie> movies) throws MDBException {
		boolean isPresent = false;

		for (Movie m : movies) {
			if (m.getMovieId() == movieId)
				isPresent = true;
		}

		if (!isPresent)
			throw new MDBException("Movie id not present");
	}

	public void validateShowByID(int showId, List<Shows> showsList) throws MDBException {
		boolean isPresent = false;
		for (Shows s : showsList) {
			if (s.getShowID() == showId)
				isPresent = true;
		}

		if (!isPresent)
			throw new MDBException("Show id not present");
	}

}

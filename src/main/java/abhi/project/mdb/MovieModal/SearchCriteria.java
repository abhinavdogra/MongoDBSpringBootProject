package abhi.project.mdb.MovieModal;

import java.util.Date;

public class SearchCriteria {
	private Object movieID;
	private Object showID;
	private String movieName;
	private String showName;
	private Date dateOfRelease;

	public Object getMovieID() {
		return movieID;
	}

	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}

	public Object getShowID() {
		return showID;
	}

	public void setShowID(String showID) {
		this.showID = showID;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public Date getDateOfRelease() {
		return dateOfRelease;
	}

	public void setDateOfRelease(Date dateOfRelease) {
		this.dateOfRelease = dateOfRelease;
	}

	public void setMovieID(Object movieID) {
		this.movieID = movieID;
	}

	public void setShowID(Object showID) {
		this.showID = showID;
	}

}

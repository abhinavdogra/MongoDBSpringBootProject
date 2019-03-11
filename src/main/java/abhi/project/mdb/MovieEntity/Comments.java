package abhi.project.mdb.MovieEntity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class Comments {
	private String comment;
	private int movieID;
	private int showID;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	public int getShowID() {
		return showID;
	}

	public void setShowID(int showID) {
		this.showID = showID;
	}

}

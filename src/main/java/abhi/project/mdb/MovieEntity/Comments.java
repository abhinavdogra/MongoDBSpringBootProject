package abhi.project.mdb.MovieEntity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class Comments {
	private String comment;
	private Object movieID;
	private Object showID;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Object getMovieID() {
		return movieID;
	}

	public void setMovieID(Object movieID) {
		this.movieID = movieID;
	}

	public Object getShowID() {
		return showID;
	}

	public void setShowID(Object showID) {
		this.showID = showID;
	}
}

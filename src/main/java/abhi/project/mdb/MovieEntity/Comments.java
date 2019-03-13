package abhi.project.mdb.MovieEntity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "comments")
public class Comments {
	
	@Id
	private ObjectId _id;
	private String comment;
	@Field
	@Indexed
	private int movieId;
	@Field
	@Indexed
	private int showID;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getShowID() {
		return showID;
	}

	public void setShowID(int showID) {
		this.showID = showID;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

}

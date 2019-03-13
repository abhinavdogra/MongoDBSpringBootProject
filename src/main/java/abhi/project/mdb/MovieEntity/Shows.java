package abhi.project.mdb.MovieEntity;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "shows")
public class Shows {

	/*
	 * { "_id": ObjectId, "showId" : ObjectId, "showName" : String,
	 * "showDescription" : String, "dateOfRelease" : Localdate, "channels" :
	 * List<String>, "cast" : Object { "actor" : List<String> "producer" :
	 * List<String> "director" : List<String> }, "rating" : int, "duration" : double
	 * }
	 */

	@Id
	private ObjectId _id;
	@Field
	@Indexed(unique=true)
	private int showID;
	@Field
	private String showName;
	private String showDescription;
	@Field
	private Date dateOfRelease;
	private List<String> channels;
	@Field
	private Cast cast;
	@Field
	private double duration;
	private int rating;

	public int getShowID() {
		return showID;
	}

	public void setShowID(int showID) {
		this.showID = showID;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getShowDescription() {
		return showDescription;
	}

	public void setShowDescription(String showDescription) {
		this.showDescription = showDescription;
	}

	public Date getDateOfRelease() {
		return dateOfRelease;
	}

	public void setDateOfRelease(Date dateOfRelease) {
		this.dateOfRelease = dateOfRelease;
	}

	public List<String> getChannels() {
		return channels;
	}

	public void setChannels(List<String> channels) {
		this.channels = channels;
	}

	public Cast getCast() {
		return cast;
	}

	public void setCast(Cast cast) {
		this.cast = cast;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

}

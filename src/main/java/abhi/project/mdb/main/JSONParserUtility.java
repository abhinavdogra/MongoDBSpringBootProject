package abhi.project.mdb.main;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

import abhi.project.mdb.MovieEntity.Movie;
import abhi.project.mdb.MovieEntity.Shows;

public class JSONParserUtility {

	// {
	// "_id": ObjectId,
	// "movieId:" : ObjectId,
	// "movieName" : String,
	// "movieDescription" : String,
	// "dateOfRelease" : Localdate,
	// "theatre" : List<String>,
	// "cast" : Object {
	// "actor" : List<String>
	// "producer" : List<String>
	// "director" : List<String>
	// },
	// "rating" : int,
	// "duration" : double,
	// }

	public static JSONArray createJSONObjectForMovie(List<Movie> movies)
			throws JSONException, JsonGenerationException, JsonMappingException, IOException {
		JSONArray arr = new JSONArray();
		for (Movie movie : movies) {
			ObjectMapper map = new ObjectMapper();
			String target = map.writeValueAsString(movie);
			arr.put(target);
		}

		return arr;

	}
	
	public static JSONArray createJSONObjectForShows(List<Shows> shows)
			throws JSONException, JsonGenerationException, JsonMappingException, IOException {
		JSONArray arr = new JSONArray();
		for (Shows show : shows) {
			ObjectMapper map = new ObjectMapper();
			String target = map.writeValueAsString(show);
			arr.put(target);
		}

		return arr;

	}

}

package abhi.project.mdb.MovieEntity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Theaters {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

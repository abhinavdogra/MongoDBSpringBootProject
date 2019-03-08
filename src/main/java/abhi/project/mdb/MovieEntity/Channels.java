package abhi.project.mdb.MovieEntity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="channels")
public class Channels {

	private String Channel;

	public String getChannel() {
		return Channel;
	}

	public void setChannel(String channel) {
		Channel = channel;
	}
}

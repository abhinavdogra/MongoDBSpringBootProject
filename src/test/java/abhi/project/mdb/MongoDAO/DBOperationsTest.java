package abhi.project.mdb.MongoDAO;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import abhi.project.mdb.MovieEntity.Comments;
import abhi.project.mdb.MovieEntity.Movie;
import abhi.project.mdb.MovieEntity.Shows;

@DataMongoTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MongoDBConfiguration.class})
//used for maintaining the order of methods in which they are writted.
//Its done here because total count which we are updating in init() will change after update for movie/show repo
@FixMethodOrder(MethodSorters.DEFAULT)
public class DBOperationsTest {

	@Autowired
	IMovieRepository movieRepo;

	@Autowired
	IShowRepository showRepo;

	@Mock
	DBOperations ops;
	
	@Autowired
	ICommentsRepository commentsRepo;
	
	static int totalMovies;
	static int totalShows;
	static int totalComments;
	
	@Before
	public void init() {
		totalMovies = movieRepo.findAll().size();
		totalShows = showRepo.findAll().size();
		totalComments = commentsRepo.findAll().size();
	}
	
	@Test
	public void getMoviesCountTest() {
		List<Movie> list = movieRepo.findAll();
		assertEquals(totalMovies, list.size());
	}
	
	@Test
	public void getShowsCountTest() {
		List<Shows> list = showRepo.findAll();
		assertEquals(totalShows, list.size());
	}
	
	@Test
	public void getCommentsCountTest() {
		List<Comments> list = commentsRepo.findAll();
		assertEquals(totalComments, list.size());
	}
	
	@Test
	public void getMoviesCountAddTest() {
		Movie movie = new Movie();
		int random = (int)Math.random() * 50000 + 1;
		movie.setMovieName("ABCD1"+random);
		movie.setDuration(12.34);
		Date date = new GregorianCalendar(2018, Calendar.FEBRUARY, 11).getTime();
		movie.setDateOfRelease(date);
		movie.setMovieId(totalMovies+1);
		
		movieRepo.save(movie);
		
		List<Movie> list = movieRepo.findAll();
		assertEquals(totalMovies+1, list.size());
	}
	
	@Test
	public void getShowsCountAddTest() {
		Shows shows = new Shows();
		int random = (int)Math.random() * 50000 + 1;
		shows.setShowName("ABCD"+random);
		shows.setDuration(12.34);
		Date date = new GregorianCalendar(2018, Calendar.FEBRUARY, 11).getTime();
		shows.setDateOfRelease(date);
		shows.setShowID(totalShows+1);
		
		showRepo.save(shows);
		
		List<Shows> list = showRepo.findAll();
		assertEquals(totalShows+1, list.size());
	}
}

package abhi.project.mdb.Validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import abhi.project.mdb.MDBException.MDBException;
import abhi.project.mdb.MovieEntity.Movie;
import abhi.project.mdb.MovieEntity.Shows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MDBValidationTest {

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	List<Movie> movies = new ArrayList<>();

	List<Shows> shows = new ArrayList<>();

	@Test
	public void validateMovieNameTest() {
		MDBValidation valid = new MDBValidation();
		Movie movie = new Movie();
		movie.setMovieName("DDLJ");

		movies.add(movie);

		String val = "";

		try {
			valid.validateMovieName("DD123LJ", movies);
		} catch (MDBException e) {
			val = e.getMessage();
		}

		assertTrue("".equalsIgnoreCase(val));
	}

	@Test
	public void validateMovieNameTestNegative() {
		MDBValidation valid = new MDBValidation();
		Movie movie = new Movie();
		movie.setMovieName("DDLJ");

		movies.add(movie);

		String val = "";

		try {
			valid.validateMovieName("DDLJ", movies);
		} catch (MDBException e) {
			val = e.getMessage();
			assertEquals("Movie already exist", val);
		}

	}

	@Test
	public void validateShowName() {
		MDBValidation valid = new MDBValidation();
		Shows show = new Shows();
		show.setShowName("DDLJ");

		shows.add(show);

		String val = "";

		try {
			valid.validateShowName("DD123LJ", shows);
		} catch (MDBException e) {
			val = e.getMessage();
		}

		assertEquals("", val);
	}

	@Test
	public void validateShowNameNegative() {
		MDBValidation valid = new MDBValidation();
		Shows show = new Shows();
		show.setShowName("DDLJ");

		shows.add(show);

		String val = "";

		try {
			valid.validateShowName("DDLJ", shows);
		} catch (MDBException e) {
			val = e.getMessage();
			assertTrue(val.equals("Show already exist"));
		}

	}

	@Test
	public void validateMovieDetails() {
		MDBValidation valid = new MDBValidation();
		Movie movie = new Movie();
		movie.setMovieName("DDLJ");
		movie.setDuration(12.92);
		Date date = new GregorianCalendar(2018, Calendar.FEBRUARY, 11).getTime();
		movie.setDateOfRelease(date);

		String val = "";

		try {
			valid.validateMovieDetails(movie);
		} catch (MDBException e) {
			val = e.getMessage();
		}
		assertEquals("", val);
	}

	@Test
	public void validateMovieDetailsNegative() {
		MDBValidation valid = new MDBValidation();
		Movie movie = new Movie();
		movie.setDuration(12.92);
		Date date = new GregorianCalendar(2018, Calendar.FEBRUARY, 11).getTime();
		movie.setDateOfRelease(date);

		String val = "";

		try {
			valid.validateMovieDetails(movie);
		} catch (MDBException e) {
			val = e.getMessage();
			assertEquals("Movie is not valid. Missing fields: Movie name is missing.", val);
		}
	}

	@Test
	public void validateShowDetails() {
		MDBValidation valid = new MDBValidation();
		Shows show = new Shows();
		show.setShowName("DDLJ");
		show.setDuration(12.92);
		Date date = new GregorianCalendar(2018, Calendar.FEBRUARY, 11).getTime();
		show.setDateOfRelease(date);

		String val = "";

		try {
			valid.validateShowDetails(show);
		} catch (MDBException e) {
			val = e.getMessage();
		}
		assertEquals("", val);

	}

	@Test
	public void validateShowDetailsNegative() {
		MDBValidation valid = new MDBValidation();
		Shows show = new Shows();
		show.setDuration(12.92);
		Date date = new GregorianCalendar(2018, Calendar.FEBRUARY, 11).getTime();
		show.setDateOfRelease(date);

		String val = "";

		try {
			valid.validateShowDetails(show);
		} catch (MDBException e) {
			val = e.getMessage();
			assertEquals("Show is not valid. Missing fields: Show name is missing.", val);
		}
	}

	@Test
	public void validateMovieByID() {
		MDBValidation valid = new MDBValidation();
		Movie movie = new Movie();
		movie.setMovieId(123);

		movies.add(movie);

		String val = "";

		try {
			valid.validateMovieByID(123, movies);
		} catch (MDBException e) {
			val = e.getMessage();
		}

		assertEquals("", val);
	}

	@Test
	public void validateMovieByIDNegative() {
		MDBValidation valid = new MDBValidation();
		Movie movie = new Movie();
		movie.setMovieId(123);

		movies.add(movie);

		String val = "";

		try {
			valid.validateMovieByID(12, movies);
		} catch (MDBException e) {
			val = e.getMessage();
			assertEquals("Movie id not present", val);
		}

	}

	@Test
	public void validateShowByID() {
		MDBValidation valid = new MDBValidation();
		Shows show = new Shows();
		show.setShowID(123);

		shows.add(show);

		String val = "";

		try {
			valid.validateShowByID(123, shows);
		} catch (MDBException e) {
			val = e.getMessage();
		}
		assertEquals("", val);
	}

	@Test
	public void validateShowByIDNegative() {
		MDBValidation valid = new MDBValidation();
		Shows show = new Shows();
		show.setShowID(123);

		shows.add(show);

		String val = "";

		try {
			valid.validateShowByID(12, shows);
		} catch (MDBException e) {
			val = e.getMessage();
			assertEquals("Show id not present", val);
		}
	}

}

package abhi.project.mdb.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import abhi.project.mdb.MovieModal.IMovies;
import abhi.project.mdb.MovieModal.MovieImpl;

@SpringBootApplication
@ComponentScan(basePackages = "abhi.project.mdb")
@Configuration
public class MDBStarter {

	public static void main(String[] args) {
		SpringApplication.run(MDBStarter.class, args);
	}
	
}

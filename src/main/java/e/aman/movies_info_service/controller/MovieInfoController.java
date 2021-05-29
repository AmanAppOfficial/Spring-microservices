package e.aman.movies_info_service.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import e.aman.movies_info_service.model.Movie;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {
	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		return new Movie(movieId , "Test description");
	}

}

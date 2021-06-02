package e.aman.movies_catalog_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import e.aman.movies_catalog_service.model.Movie;
import e.aman.movies_catalog_service.model.Rating;

@Service
public class MovieInfoService {
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="getFallbackMovie")
	public Movie getMovies(Rating rating){
		 return restTemplate.getForObject("http://movie-info-services/movies/" + rating.getMovieId(), Movie.class);
		
	}
		
	private Movie getFallbackMovie(Rating rating){
		return new Movie("No movie" , "");
	}

}

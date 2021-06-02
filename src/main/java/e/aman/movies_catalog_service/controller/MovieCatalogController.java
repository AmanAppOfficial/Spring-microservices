package e.aman.movies_catalog_service.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import e.aman.movies_catalog_service.model.Movie;
import e.aman.movies_catalog_service.model.MovieModel;
import e.aman.movies_catalog_service.model.Rating;
import e.aman.movies_catalog_service.service.MovieInfoService;
import e.aman.movies_catalog_service.service.UserRatingService;

@RestController
@RequestMapping("/catalog") 
public class MovieCatalogController {
	
	@Autowired 
	private MovieInfoService movieInfoService;
	
	@Autowired
	private UserRatingService userRatingService;


	@RequestMapping("/{userId}")
	public List<MovieModel> getCatalog(@PathVariable("userId") String userId){
		
		List<MovieModel> movieModelList = new ArrayList<>();
	
		ResponseEntity<List<Rating>> rateResponse = userRatingService.getRatings(userId);
				
			 List<Rating> ratings = rateResponse.getBody();
			 for(Rating rating : ratings) {
				 Movie m = movieInfoService.getMovies(rating);
				 movieModelList.add(new MovieModel(m.getMovieName() , "" , rating.getRating()));
			 }

		 return movieModelList;
		
		
	}
	
	
	
	
	
}

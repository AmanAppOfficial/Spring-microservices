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

import e.aman.movies_catalog_service.model.Movie;
import e.aman.movies_catalog_service.model.MovieModel;
import e.aman.movies_catalog_service.model.Rating;

@RestController
@RequestMapping("/catalog") 
public class MovieCatalogController {
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/{userId}")
	public List<MovieModel> getCatalog(@PathVariable("userId") String userId){
		
		List<MovieModel> movieModelList = new ArrayList<>();
		
		try {
			ResponseEntity<List<Rating>> rateResponse = restTemplate.exchange("http://ratings-data-service/raringsdata/users/" + userId, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Rating>>() {
		            });
				
			 List<Rating> ratings = rateResponse.getBody();
			 for(Rating rating : ratings) {
				 Movie m  = restTemplate.getForObject("http://movie-info-services/movies/" + rating.getMovieId(), Movie.class);
					movieModelList.add(new MovieModel(m.getMovieName() , "Test description" , rating.getRating()));
			 }
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
		}

		 return movieModelList;
		
		
	}
	
}

package e.aman.movies_catalog_service.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import e.aman.movies_catalog_service.model.Rating;

@Service
public class UserRatingService {
	
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod="getFallbackRating")
	public ResponseEntity<List<Rating>> getRatings(String userId){
		return restTemplate.exchange("http://ratings-data-service/raringsdata/users/" + userId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Rating>>() {
        });
	}
	
	/*
	 * Fallback method occurs when circuit trips
	 * */
	private ResponseEntity<List<Rating>> getFallbackRating(String userId){
		return ResponseEntity.ok(Arrays.asList(new Rating("No movie" , 0)));
	}
	
}

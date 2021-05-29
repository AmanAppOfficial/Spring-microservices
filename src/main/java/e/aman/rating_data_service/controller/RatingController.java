package e.aman.rating_data_service.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import e.aman.rating_data_service.model.Rating;

@RestController
@RequestMapping("/raringsdata")
public class RatingController {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId , 4);
	}
	
	@RequestMapping("/users/{userId}")
	public List<Rating> getRatingByUser(@PathVariable("userId") String userId) {
		return Arrays.asList(new Rating("1234" , 4) , new Rating("5678" , 5));
	}
	
}

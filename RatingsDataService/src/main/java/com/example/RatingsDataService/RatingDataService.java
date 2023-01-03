package com.example.RatingsDataService;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingDataService {
	
	@GetMapping("/getMovieIdsAndRatings/{userId}")
	private UserRating getMovieIdsAndRatings(@PathVariable("userId") String userId) {
		UserRating userRating = new UserRating();
		userRating.setRatingList(Arrays.asList(new Rating(1234, 487, 4), new Rating(5678, 487, 4)));
		return userRating;
	}

}

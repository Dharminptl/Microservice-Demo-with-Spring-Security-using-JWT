package com.example.MovieCatalogService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieCatalogService {
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/movie/{userId}")
	private List<CatalogItem> getUserReview(@PathVariable("userId") String userId) {

		UserRating ratings = restTemplate.getForObject("http://RATINGS-DATA-SERVICE/getMovieIdsAndRatings/" + userId,
				UserRating.class);

		return ratings.getRatingList().stream().map(rating -> {
			MovieDetails movieDetails = restTemplate.getForObject(
					"http://MOVIE-INFO-SERVICE/getMovieDetails/" + rating.getMovieId(), MovieDetails.class);
			return new CatalogItem(movieDetails.getMovieName(), movieDetails.getDescription(), rating.getRating());
		}).collect(Collectors.toList());
	}

}

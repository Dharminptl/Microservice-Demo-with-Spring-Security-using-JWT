package com.example.MovieInfoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoService {
	
	@GetMapping("/getMovieDetails/{movieId}")
	private MovieDetails getMovieDetails(@PathVariable int movieId) {
		if(movieId==1234) {
			return new MovieDetails(12,"Avengers", "2019");
		} else {
			return new MovieDetails(14,"Transformers", "2020");
		}
	}

}

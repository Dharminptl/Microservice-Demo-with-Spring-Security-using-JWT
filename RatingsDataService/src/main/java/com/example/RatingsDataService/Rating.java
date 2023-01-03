package com.example.RatingsDataService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
	
	private int movieId;

	private int userId;

	private int rating;

}

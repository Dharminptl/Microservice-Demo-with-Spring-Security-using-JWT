package com.example.MovieCatalogService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetails {

	private int movieId;

	private String movieName;

	private String description;

}

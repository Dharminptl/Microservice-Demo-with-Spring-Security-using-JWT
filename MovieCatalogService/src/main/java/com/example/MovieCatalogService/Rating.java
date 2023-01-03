package com.example.MovieCatalogService;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating implements Serializable{
	
	private int movieId;

	private int userId;

	private int rating;
	
}

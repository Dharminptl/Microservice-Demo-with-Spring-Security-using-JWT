package com.example.MovieCatalogService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogItem {

	private String movieName;

	private String movieDesc;

	private int ratings;
	
}

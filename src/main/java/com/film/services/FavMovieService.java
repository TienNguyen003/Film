package com.film.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.film.models.FavMovie;

public interface FavMovieService {
	List<FavMovie> findByUserFav(int id);	
	Boolean update(FavMovie favMovie);
	FavMovie findBySlugAndUser_fav(String slug, int id);
	Boolean delete(int id);
	
	Page<FavMovie> getPaginatedRecordsForUser(int pageNumber, int pageSize, int id);
}

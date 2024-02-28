package com.film.services;

import java.util.List;

import com.film.models.Genres;

public interface GenresService {
	List<Genres> getAll();
	Boolean create(Genres category);
	Genres findById(Integer id);
	Boolean update(Genres category);
	Boolean delete(Integer id);
}

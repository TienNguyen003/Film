package com.film.services;

import java.util.List;

import com.film.models.Genres;

public interface GenresService {
	List<Genres> getAll();
	Boolean create(Genres genres);
	Genres findById(Integer id);
	Boolean update(Genres genres);
	Boolean delete(Integer id);
}

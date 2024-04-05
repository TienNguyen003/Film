package com.film.services;

import java.util.List;

import com.film.models.FilmModel;

public interface FilmService {
	List<FilmModel> getAll();
	Boolean save(FilmModel filmModel);
	Boolean delete(int id);
	FilmModel getById(int id);
}

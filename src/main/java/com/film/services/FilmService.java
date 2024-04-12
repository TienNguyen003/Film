package com.film.services;

import java.util.List;

import com.film.models.FilmModel;

public interface FilmService {
	List<FilmModel> getAll();
	List<FilmModel> findRandAll();
	Boolean save(FilmModel filmModel);
	Boolean delete(int id);
	FilmModel getById(int id);
}

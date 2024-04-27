package com.film.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.film.models.FilmModel;

public interface FilmService {
	int findViewBySlug(String slug);
	
	List<String> findSlugFilm();
	
	List<FilmModel> getAll();
	
	List<FilmModel> findRandAll();
	
	List<FilmModel> findAllByView();
	
	List<FilmModel> findByTypeGenres(String type, String genres, String slug);
	
	Boolean updateViewBySlug(String slug, int view);
	
	Boolean save(FilmModel filmModel);
	
	Boolean delete(int id);
	
	FilmModel getById(int id);
	
	Page<FilmModel> getPaginatedRecords(int pageNumber, int pageSize);
}

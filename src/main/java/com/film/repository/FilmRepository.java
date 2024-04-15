package com.film.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.film.models.FilmModel;

public interface FilmRepository extends JpaRepository<FilmModel, Integer>{
	
	@Query(value = "SELECT * FROM film ORDER BY RAND() LIMIT 5", nativeQuery = true)
	List<FilmModel> findRandAll();
	
	@Query(value = "SELECT * FROM webfilm.film ORDER BY view DESC LIMIT 5", nativeQuery = true)
	List<FilmModel> findAllByView();
	
}

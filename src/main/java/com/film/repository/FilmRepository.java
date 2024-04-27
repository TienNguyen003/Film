package com.film.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.film.models.FilmModel;

import jakarta.transaction.Transactional;

public interface FilmRepository extends JpaRepository<FilmModel, Integer>{
	@Query(value = "SELECT slug FROM webfilm.film", nativeQuery = true)
	List<String> findSlugFilm();
	
	@Query(value = "SELECT view FROM webfilm.film WHERE slug = :slug", nativeQuery = true)
	int findViewBySlug(@Param("slug") String slug);

	@Query(value = "SELECT * FROM film ORDER BY RAND() LIMIT 5", nativeQuery = true)
	List<FilmModel> findRandAll();
	
	@Query(value = "SELECT * FROM webfilm.film ORDER BY view DESC LIMIT 5", nativeQuery = true)
	List<FilmModel> findAllByView();
	
	@Query(value = "SELECT * FROM webfilm.film WHERE type = :type AND id_genres REGEXP :genres AND slug NOT LIKE :slug ORDER BY RAND() LIMIT 5", nativeQuery = true)
	List<FilmModel> findByTypeGenres(@Param("type") String type, @Param("genres") String genres, @Param("slug") String slug);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE film SET view = :view WHERE slug = :slug", nativeQuery = true)
	public void updateViewBySlug(@Param("slug") String slug, @Param("view") int view);	
	
	@Query("SELECT f FROM FilmModel f")
	Page<FilmModel> findMovie(Pageable pageable);
}

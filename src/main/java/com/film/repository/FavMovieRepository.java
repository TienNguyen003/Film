package com.film.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.film.models.FavMovie;


public interface FavMovieRepository extends JpaRepository<FavMovie, Integer>{
	
	@Query(value = "SELECT * FROM favmovie f WHERE f.slug = :slug and f.user_fav = :user_fav", nativeQuery = true)
	FavMovie findBySlugAndUser_fav(@Param("slug") String slug, @Param("user_fav") Integer user_fav);
	
	@Query(value = "select * from favmovie f where f.user_fav = :user_fav", nativeQuery = true)
	List<FavMovie> findByUserFav(@Param("user_fav") Integer user_fav);
	
	@Query(value = "SELECT category FROM favmovie where user_fav = :user_fav", nativeQuery = true)
	List<String> findCategoryById(@Param("user_fav") int user_fav);
	
	Page<FavMovie> findMovieByUser(int user_fav, Pageable pageable);
}

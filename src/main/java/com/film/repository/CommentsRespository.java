package com.film.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.film.models.Comments;

public interface CommentsRespository extends JpaRepository<Comments, Integer>{
	@Query(value = "SELECT * FROM comments c WHERE c.slug_film = :slug ORDER BY id DESC LIMIT 6", nativeQuery = true)
	List<Comments> findBySlug(@Param("slug") String slug);
}

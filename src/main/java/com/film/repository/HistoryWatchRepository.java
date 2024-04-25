package com.film.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.film.models.HistoryWatch;

public interface HistoryWatchRepository extends JpaRepository<HistoryWatch, Integer>{

	@Query(value = "SELECT * FROM history_watch u WHERE u.slug = :slug and u.user_watch = :user_watch", nativeQuery = true)	
	HistoryWatch findByUser_watchAndSlug(@Param("slug") String slug, @Param("user_watch") Long user_watch);
		
	Page<HistoryWatch> findHistoryByUser(int user_watch, Pageable pageable);
}

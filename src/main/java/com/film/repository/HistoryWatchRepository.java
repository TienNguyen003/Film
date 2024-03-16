package com.film.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.film.models.HistoryWatch;

public interface HistoryWatchRepository extends JpaRepository<HistoryWatch, Integer>{

	@Query(value = "SELECT * FROM history_watch u WHERE u.slug = :slug and u.user_watch = :user_watch", nativeQuery = true)
	HistoryWatch findByUser_watchAndSlug(@Param("slug") String slug, @Param("user_watch") Long user_watch);
	@Query(value = "SELECT * FROM history_watch u WHERE u.user_watch = :user_watch", nativeQuery = true)
	List<HistoryWatch> findByUser_watch(@Param("user_watch") Long user_watch);
}

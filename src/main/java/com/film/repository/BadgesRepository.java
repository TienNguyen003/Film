package com.film.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.film.models.Badges;

public interface BadgesRepository extends JpaRepository<Badges, Integer>{
	@Query(value = "SELECT image FROM badges WHERE id IN (:id)", nativeQuery = true)
	List<String> findImageById(@Param("id") List<Integer> id);
}

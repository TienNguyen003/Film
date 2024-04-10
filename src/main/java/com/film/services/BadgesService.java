package com.film.services;

import java.util.List;

import com.film.models.Badges;

public interface BadgesService {
	List<Badges> getAll();
	List<String> findImageById(String id);
	Boolean create(Badges badges);
	Boolean delete(Integer id);
}

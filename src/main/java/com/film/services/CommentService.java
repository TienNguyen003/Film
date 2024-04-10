package com.film.services;

import java.util.List;

import com.film.models.Comments;

public interface CommentService {
	List<Comments> findBySlug(String slug);
	String findCreateById(String slug, int id);
	Boolean save(Comments comments);
	Boolean delete(int id);
	Boolean updateImage(String image, int id);
	Boolean updateNameUser(String name, int id);
}

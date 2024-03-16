package com.film.services;

import java.util.List;

import com.film.models.Comments;

public interface CommentService {
	List<Comments> findBySlug(String slug);
	Boolean save(Comments comments);
}

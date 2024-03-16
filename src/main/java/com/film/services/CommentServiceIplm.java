package com.film.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.film.models.Comments;
import com.film.repository.CommentsRespository;

public class CommentServiceIplm implements CommentService{
	@Autowired
	private CommentsRespository commentsRespository;

	@Override
	public List<Comments> findBySlug(String slug) {
		return this.commentsRespository.findBySlug(slug);
	}

	@Override
	public Boolean save(Comments comments) {
		try {
			this.commentsRespository.save(comments);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}

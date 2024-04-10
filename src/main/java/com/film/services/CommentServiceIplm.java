package com.film.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.film.models.Comments;
import com.film.repository.CommentsRespository;

@Service
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
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean delete(int id) {
		try {
			this.commentsRespository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String findCreateById(String slug, int id) {
		// TODO Auto-generated method stub
		System.out.println(slug + " " + id);
		return this.commentsRespository.findCreateById(slug, id);
	}

	@Override
	public Boolean updateImage(String image, int id) {
		try {
			this.commentsRespository.updateImage(image, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean updateNameUser(String name, int id) {
		try {
			this.commentsRespository.updateNameUser(name, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}

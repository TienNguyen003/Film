package com.film.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.film.models.Comments;
import com.film.repository.CommentsRespository;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	 @Autowired
	 private CommentsRespository commentRepository;

	 @GetMapping("/{slug}")
	 public List<Comments> getCommentsSlug(@PathVariable String slug) {
	    return commentRepository.findBySlug(slug);
	 }
	 @PostMapping("/save")
	 public ResponseEntity<Comments> createComment(@RequestBody Comments comment) {
		 Comments savedComment = commentRepository.save(comment);
	     return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
	 }
}

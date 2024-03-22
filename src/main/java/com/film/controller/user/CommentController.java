package com.film.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.film.models.Comments;
import com.film.repository.CommentsRespository;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	LoadController loadController = new LoadController();
	
	private CommentController(LoadController loadController) {
        this.loadController = loadController;
    }
	
	 @Autowired
	 private CommentsRespository commentRepository;

	 @GetMapping("/{slug}")
	 public List<Comments> getCommentsSlug(@PathVariable String slug) {
	    return commentRepository.findBySlug(slug);
	 }
	 
	 @PostMapping("/save")
	 public ResponseEntity<Comments> createComment(
			@RequestParam(required = false, defaultValue = "") int id,
			@RequestParam(required = false, defaultValue = "") String slug,
			@RequestParam(required = false, defaultValue = "") String contentCm) {
		 int idUser = loadController.getUserIdFromUserDetails().intValue();
		 if(idUser > 0) {
			 String img = loadController.csUser().getUser().getImg();
				if(img == null) img = "https://i.pinimg.com/564x/a2/2d/1d/a22d1d8a789a904187dba0f5240a5907.jpg";
				String name = loadController.csUser().getUser().getFullName();
				String createAt = loadController.dateTime();
				Comments newComments = new Comments();
				if(id > 0) newComments.setId(id);
			 	newComments.setSlugFilm(slug);
			 	newComments.setContent(contentCm);
			 	newComments.setCreateAt(createAt);
			 	newComments.setImage(img);
			 	newComments.setName(name);
			 	newComments.setIdUser(idUser);
			 	commentRepository.save(newComments);
		 	return ResponseEntity.ok(newComments);
		 }
		 return null;
	 }
}

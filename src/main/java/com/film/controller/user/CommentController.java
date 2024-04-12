package com.film.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.film.models.Comments;
import com.film.services.BadgesService;
import com.film.services.CommentService;
import com.film.services.UserService;

import org.springframework.ui.Model;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	LoadController loadController = new LoadController();
	
	private CommentController(LoadController loadController) {
        this.loadController = loadController;
    }
	
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private BadgesService badgesService;

	@GetMapping("/{slug}")
	public List<Map<String, Object>> getCommentsSlug(@PathVariable String slug, Model model) {	    
	    List<Map<String, Object>> resultList = new ArrayList<>();
	    
	    List<Comments> comments = commentService.findBySlug(slug);
	    for (Comments comment : comments) {
	        String user_badges = userService.findBadgesById(comment.getIdUser());
	        if (user_badges.startsWith(",")) {
	        	user_badges = user_badges.substring(1);
	        }
	        List<String> images = badgesService.findImageById(user_badges);
	        
	        Map<String, Object> commentData = new HashMap<>();
	        commentData.put("comment", comment);
	        commentData.put("images", images);
	        commentData.put("quantity", commentService.findAllCmt(slug));
	        resultList.add(commentData);
	    }
	    return resultList;
	}
	 
	@PostMapping("/save")
	public ResponseEntity<Comments> createComment(
			@RequestParam(required = false, defaultValue = "") String id,
			@RequestParam(required = false, defaultValue = "") String slug,
			@RequestParam(required = false, defaultValue = "") String contentCm) {
		 int idUser = loadController.getUserIdFromUserDetails().intValue();
		 if(idUser > 0) {
			 String img = loadController.csUser().getUser().getImg();
				if(img == null) img = "https://i.pinimg.com/564x/a2/2d/1d/a22d1d8a789a904187dba0f5240a5907.jpg";
				String name = loadController.csUser().getUser().getFullName();
				String createAt = loadController.dateTime();
				Comments newComments = new Comments();
				if(!contentCm.equals("")) {
					if(!id.isEmpty()) {
						String getCreate = commentService.findCreateById(slug, Integer.parseInt(id));
						newComments.setId(Integer.parseInt(id));
						newComments.setEdit_comment("1");
						newComments.setCreateAt(getCreate);
					}
					else {
						newComments.setEdit_comment("0");
						newComments.setCreateAt(createAt);
					}
			 		newComments.setSlugFilm(slug);
			 		newComments.setContent(contentCm.trim());
			 		newComments.setImage(img);
			 		newComments.setName(name);
			 		newComments.setIdUser(idUser);
			 		commentService.save(newComments);
			 		return ResponseEntity.ok(newComments);
				}
		}
		return null;
	}
	 
	 @PostMapping("/delete")
	 public ResponseEntity<String> deleteComment(
			 @RequestParam(required = false, defaultValue = "") String id){
		 int idUser = loadController.getUserIdFromUserDetails().intValue();
		 if(idUser > 0) {
			 commentService.delete(Integer.parseInt(id));
			 return ResponseEntity.ok(id);
		 }
		 return null;
	 }
}

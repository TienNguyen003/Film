package com.film.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {	
	LoadController loadController = new LoadController();
	
	private BlogController(LoadController loadController) {
        this.loadController = loadController;
    }
	
	@GetMapping("/our-blog")
	public String categoryBlog(Model model) {
		loadController.categoryShow(model);
		loadController.genresShow(model);
		return "blog";
	}
	
	@GetMapping("/blog-detail")
	public String blogDetail(Model model) {
		loadController.categoryShow(model);
		loadController.genresShow(model);
		return "blog-details";
	}
}

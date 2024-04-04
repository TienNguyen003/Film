package com.film.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.film.models.Category;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@GetMapping
	public String index() {
		return "redirect:/admin";
	}
	@GetMapping("/")
	public String admin() {
		return "admin/index";
	}
	
	@GetMapping("/film")
	public String adminFilm() {
		return "admin/film";
	}
	
	@GetMapping("/add-film")
	public String add(Model model) {
		return "admin/add-film";
	}	
	
	@GetMapping("/edit-film")
	public String adminEditFilm() {
		return "admin/edit-film";
	}
}

package com.film.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.film.controller.user.LoadController;
import com.film.models.FilmModel;
import com.film.services.FilmService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	LoadController loadController = new LoadController();
	
	private AdminController(LoadController loadController) {
        this.loadController = loadController;
    }
	
	@Autowired
	private FilmService filmService;
	
	@GetMapping
	public String index() {
		return "redirect:/admin";
	}
	
	@GetMapping("/")
	public String admin() {
		return "admin/index";
	}
	
	@GetMapping("/add-post")
	public String adminPost() {
		return "admin/add-post";
	}
	
	@GetMapping("/film")
	public String adminFilm(Model model, @RequestParam(name="page", defaultValue = "1") int page) {
		Page<FilmModel> list = filmService.getPaginatedRecords(page - 1, 6);
		if(list.getContent().size() <= 0) model.addAttribute("checkLogin", "Chưa có bộ phim nào !!!");
		else {
			model.addAttribute("list", list);
			model.addAttribute("totalPage", list.getTotalPages());
			model.addAttribute("currentPage", page);
		}		
		return "admin/film";
	}
	
	@GetMapping("/post")
	public String posts() {
		return "admin/post";
	}
	
	@GetMapping("/add-film")
	public String add(Model model) {
		FilmModel filmModel = new FilmModel();
		model.addAttribute("film", filmModel);
		return "admin/add-film";
	}
	@PostMapping("/add-film")
	public String save(@ModelAttribute("film") FilmModel film) {
		if(!film.getName().equals("")) {
			String createAt = loadController.dateTime();
			if(film.getId() != null) {
				film.setModified(createAt);
			}
			else {
				film.setCreated(createAt);	
				film.setModified(createAt);
			}				
			this.filmService.save(film);
			return "redirect:/admin/film";			
		} else {
			return "admin/add-film";
		}
	}
	
	@GetMapping("/edit-film/{id}")
	public String adminEditFilm(Model model, @PathVariable(name = "id", required = false) int id) {
		FilmModel filmModel = this.filmService.getById(id);
		model.addAttribute("film", filmModel);
		return "admin/add-film";
	}
}

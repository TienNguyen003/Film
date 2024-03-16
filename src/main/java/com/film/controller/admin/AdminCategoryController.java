package com.film.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.film.models.Category;
import com.film.services.CategoryService;

@Controller
@RequestMapping("/admin")
public class AdminCategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/category")
	public String index(Model model) {
		List<Category> listCate = this.categoryService.getAll();;
		model.addAttribute("list", listCate);
		return "admin/menu";
	}
	
	@GetMapping("/add-category")
	public String add(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "admin/add-menu";
	}	
	
	@PostMapping("/add-category")
	public String save(@ModelAttribute("category") Category category) {
		if(this.categoryService.create(category)) {
			return "redirect:/admin/category";			
		} else {
			return "admin/add-menu";
		}
	}
	
	@RequestMapping("/editCategory/{id}")
	public String edit(Model model, @PathVariable(name="id") int id) {
		Category category = categoryService.findById(id);
		model.addAttribute("category", category);
		return "/admin/edit-menu";
	}
	
	@PostMapping("/updateCategory")
	public String editCate(@ModelAttribute("category") Category category) {
		if(this.categoryService.create(category)) {
			return "redirect:/admin/category";			
		} else {
			return "admin/category";
		}
	}
	
	@RequestMapping("/deleteCategory/{id}")
	public String delete(@PathVariable(name="id") int id) {
		this.categoryService.delete(id);
		return "redirect:/admin/category";
	}
}

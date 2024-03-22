package com.film.controller.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.film.models.UserModel;
import com.film.services.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class UserController {
	LoadController loadController = new LoadController();

	@Autowired
	private UserService userService;	
	
	private UserController(LoadController loadController) {
		this.loadController = loadController;
	}
	
	@GetMapping("/")
	public String category(Model model) {
		loadController.categoryShow(model);
		loadController.genresShow(model);
		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		loadController.categoryShow(model);
		loadController.genresShow(model);
		return "login";
	}
	
	@GetMapping("/genres")
	public String categories(Model model) {
		loadController.categoryShow(model);
		loadController.genresShow(model);
		return "categorie";
	}
	
	@GetMapping("/categories")
	public String categorie(Model model) {
		loadController.categoryShow(model);
		loadController.genresShow(model);
		return "categories";
	}	
	
	@GetMapping("/signup")
	public String signup(Model model, RedirectAttributes redirectAttributes) {
		loadController.categoryShow(model);
		loadController.genresShow(model);	
		model.addAttribute("user", new UserModel());
		if(redirectAttributes.getAttribute("notification") != null) model.addAttribute("notification", redirectAttributes.getAttribute("notification"));
		return "signup";
	}	
	@PostMapping("/signup")
	public String signupUser(@ModelAttribute("user") UserModel user, RedirectAttributes redirectAttributes) {	
		if(userService.findByUser(user.getUserName()) > 0) {
			redirectAttributes.addFlashAttribute("notification", "*Tên tài khoản đã được đăng ký*");
			return "redirect:/signup";
		} else if(userService.findByEmail(user.getEmail()) > 0) {
			redirectAttributes.addFlashAttribute("notification", "*Email đã được đăng ký*");
			return "redirect:/signup";
		} else if(user.getUserName() == "") {
			redirectAttributes.addFlashAttribute("notification", "*Tên tài khoản không được để trống*");
			return "redirect:/signup";
		} else if(user.getEmail() == "") {
			redirectAttributes.addFlashAttribute("notification", "*Email không được để trống*");
			return "redirect:/signup";
		} else {
			// mã hóa mk
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    	String hashedPassword = encoder.encode(user.getPassWord());
			user.setPassWord(hashedPassword);

	        // Định dạng ngày theo chuẩn "dd/MM/yyyy"
			LocalDate now = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        user.setCreateAt(now.format(formatter));
	        
			userService.save(user);
		}
		return "redirect:/login";
	}	
}

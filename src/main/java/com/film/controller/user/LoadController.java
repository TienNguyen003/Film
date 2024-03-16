package com.film.controller.user;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.film.models.Category;
import com.film.models.CustomUserDetails;
import com.film.models.Genres;
import com.film.services.CategoryService;
import com.film.services.GenresService;

@Controller
public class LoadController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private GenresService genresService;
	
	public Model categoryShow(Model model) {
		List<Category> list = this.categoryService.getAll();
		model.addAttribute("list", list);
		return model;
	}
	
	public Model genresShow(Model model) {
		List<Genres> listGenres = this.genresService.getAll();
		model.addAttribute("listGenres", listGenres);
		return model;
	}
	
	public Long getUserIdFromUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = null;
		Long userId = 0L;
		if (authentication.getPrincipal() instanceof UserDetails) {
			// trả về một đối tượng Object đại diện cho thông tin người dùng
            userDetails = (UserDetails) authentication.getPrincipal();
		}
        if (userDetails instanceof CustomUserDetails) {
        	CustomUserDetails yourUserDetails = (CustomUserDetails) userDetails;
        	userId = yourUserDetails.getId();
        }
        return userId;
    }
	
	public static String decodeBase64(String base64EncodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64EncodedString);
        return new String(decodedBytes);
    }
}

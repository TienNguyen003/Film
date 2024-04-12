package com.film.controller.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.film.models.Badges;
import com.film.models.FilmModel;
import com.film.models.UserModel;
import com.film.services.BadgesService;
import com.film.services.FavMovieService;
import com.film.services.FilmService;
import com.film.services.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
	LoadController loadController = new LoadController();

	@Autowired
	private UserService userService;
	@Autowired
	private FavMovieService favMovieService;
	@Autowired
	private BadgesService badgesService;
	@Autowired
	private FilmService filmService;
	
	private UserController(LoadController loadController) {
		this.loadController = loadController;
	}
	
	@GetMapping("/")
	public String category(Model model) {
		loadController.categoryShow(model);
		loadController.genresShow(model);
		int idUser = loadController.getUserIdFromUserDetails().intValue();
		if(idUser > 0) {
			List<String> geners = (favMovieService.findCategoryById(idUser));

	        Map<String, Integer> genreCount = new HashMap<>();
	        for (String genre : geners) {
	            String[] splitGenres = genre.split(",");
	            for (String splitGenre : splitGenres) {
	                String cleanedGenre = splitGenre.trim();
	                if (genreCount.containsKey(cleanedGenre)) {
	                    genreCount.put(cleanedGenre, genreCount.get(cleanedGenre) + 1);
	                } else {
	                    genreCount.put(cleanedGenre, 1);
	                }
	            }
	        }

	        int maxFrequency = 0;
	        for (int frequency : genreCount.values()) {
	            if (frequency > maxFrequency) {
	                maxFrequency = frequency;
	            }
	        }
	        for (Map.Entry<String, Integer> entry : genreCount.entrySet()) {
	            if (entry.getValue() == maxFrequency) {
	            	if(entry.getValue() > 3)
	            		model.addAttribute("geners", entry.getKey());
	            }
	        }
		}
		List<FilmModel> list = this.filmService.findRandAll();
		model.addAttribute("listBanner", list);
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
	
	@GetMapping("/tu-bao-cac")
	public String shop(Model model) {
		int idUser = loadController.getUserIdFromUserDetails().intValue();
		String user_badges = userService.findBadgesById(idUser);
		List<Badges> badges = badgesService.getAll();
		List<Integer> integerList = new ArrayList<>();       
        
        if(user_badges != null) {
        	String[] stringArray = user_badges.split(",");
	        for (String str : stringArray) {  
	        	if(str != null && !str.isEmpty() && !str.equals("null"))
	        		integerList.add(Integer.parseInt(str.trim()));
	        }
	    }
        
		loadController.categoryShow(model);
		loadController.genresShow(model);
		
		model.addAttribute("listBadges", badges);
		model.addAttribute("user_badges", integerList);
		return "tu-bao-cac";
	}
	@PostMapping("/tu-bao-cac")
	public ResponseEntity<String> buyBadges(
			@RequestParam(required = false, defaultValue = "") String badges,
			@RequestParam(required = false, defaultValue = "") int id,
			@RequestParam(required = false, defaultValue = "") int price,
			@RequestParam(required = false, defaultValue = "") int point) {
		String message = "";
		Object[][] infoUser = userService.queryPointCrytalById(id);
		int crystal = (int) infoUser[0][1];
		if(crystal < price) message = "Không đủ tinh thạch";
		else {
			userService.updateBadgesById(infoUser[0][2] + ", " + badges, id, (int)infoUser[0][0] + point, crystal - price);
			message = "Mua thành công";
			loadController.csUser().getUser().setCrystal(crystal - price);
			loadController.csUser().getUser().setPoint((int)infoUser[0][0] + point);
		}
		return ResponseEntity.ok(String.valueOf(message));
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

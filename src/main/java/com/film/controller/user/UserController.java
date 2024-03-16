package com.film.controller.user;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.film.models.FavMovie;
import com.film.models.HistoryWatch;
import com.film.models.UserModel;
import com.film.services.FavMovieService;
import com.film.services.HistoryWatchService;
import com.film.services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class UserController {
	LoadController loadController = new LoadController();

	@Autowired
	private UserService userService;
	@Autowired
	private HistoryWatchService historyWatchService;
	@Autowired
	private FavMovieService favMovieService;	
	
	public UserController(LoadController loadController) {
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
	
	@GetMapping("/watch-film")
	public String watchFilm(Model model, 
			@RequestParam(name = "slug") String slug, 
			@RequestParam(name = "episodes", defaultValue = "1") int episodes,
			HttpServletRequest request) throws Exception {
		loadController.categoryShow(model);
		loadController.genresShow(model);
		Cookie[] cookies = request.getCookies();
		String name_movie = "";
		String img_movie = "";
		int view_movie = 0;
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("name_movie")) {
				name_movie = cookie.getValue();	
				String decodedBase64String = LoadController.decodeBase64(name_movie);
		        String decodedString = URLDecoder.decode(decodedBase64String, "UTF-8");
		        name_movie = decodedString;
			}
			if(cookie.getName().equals("img_movie")) img_movie = cookie.getValue();
			if(cookie.getName().equals("view_movie")) view_movie = Integer.valueOf(cookie.getValue());
		}
		Long idWatch = loadController.getUserIdFromUserDetails();
		HistoryWatch watch = historyWatchService.findByUser_watchAndSlug(idWatch, slug);
		if(idWatch > 0) {
			HistoryWatch historyWatch = new HistoryWatch();
			historyWatch.setEpisode(episodes);
			historyWatch.setUser_watch(idWatch.intValue());
			historyWatch.setSlug(slug);
			historyWatch.setImg_movie(img_movie);
			historyWatch.setView_movie(view_movie);
			historyWatch.setName_movie(name_movie);
			if(watch != null) {historyWatch.setId(watch.getId());}
			historyWatchService.update(historyWatch);
		}
		return "anime-watching";
	}
	
	@GetMapping("/detail-film")
	public String detailFilm(Model model, @RequestParam(name = "slug") String slug) {
		loadController.categoryShow(model);
		loadController.genresShow(model);
		Long idWatch = loadController.getUserIdFromUserDetails();
		if (idWatch != 0) {
			FavMovie returnFavMovie = favMovieService.findBySlugAndUser_fav(slug, idWatch.intValue());
    		if (returnFavMovie != null) model.addAttribute("checkMessageFav", "Đã có");
    		HistoryWatch watch = historyWatchService.findByUser_watchAndSlug(idWatch, slug);
    		model.addAttribute("history", watch);
		}
		return "anime-details";
	}
	
	@PostMapping("/detail-film")
	public String detailFilmPost(Model model,
			@RequestParam(name = "slug") String slug,
			@RequestParam String name_movie,
            @RequestParam String img_movie,
            @RequestParam String episode,
            @RequestParam String view_movie) {
		loadController.categoryShow(model);
		loadController.genresShow(model);
		Long idWatch = loadController.getUserIdFromUserDetails();
		if (idWatch != 0) {
			FavMovie favMovie = new FavMovie();
			favMovie.setUser(idWatch.intValue());
			favMovie.setEpisode(episode);
			favMovie.setImg_movie(img_movie);
			favMovie.setName_movie(name_movie);
			favMovie.setView_movie(view_movie);
    		FavMovie returnFavMovie = favMovieService.findBySlugAndUser_fav(slug, idWatch.intValue());    		
    		if (returnFavMovie == null) {
    			favMovie.setSlug(slug);
    			if (favMovieService.update(favMovie)) model.addAttribute("successMessageFav", "Thêm thành công");
    		} else {
    			favMovieService.delete(returnFavMovie.getId());
    			model.addAttribute("successMessageFav", "Xóa thành công"); 
    		}
		} else {
			model.addAttribute("successMessageFav", "Bạn cần đăng nhập");
		}
		HistoryWatch watch = historyWatchService.findByUser_watchAndSlug(idWatch, slug);		
		model.addAttribute("history", watch);
		return "anime-details";
	}
	
	@GetMapping("/search-film")
	public String searchFilm(Model model) {
		loadController.categoryShow(model);
		loadController.genresShow(model);
		return "searchFilm";
	}
	
	@GetMapping("/favmovie")
	public String favMovie(Model model, @RequestParam(name="page", defaultValue = "1") int page) {
		loadController.categoryShow(model);
		loadController.genresShow(model);
		int idUser = loadController.getUserIdFromUserDetails().intValue();
		if(idUser != 0) {
			Page<FavMovie> list = favMovieService.getPaginatedRecordsForUser(page-1, 24, idUser);
			model.addAttribute("listFavMovie", list);	
			model.addAttribute("totalPage", list.getTotalPages());
			model.addAttribute("currentPage", page);
		} else model.addAttribute("checkLogin", "Bạn cần phải <a href='/login'>đăng nhập</a> để có thể sử dụng tính năng này");
		return "favmovie";
	}
	@GetMapping("/favmovie/delete/{id}")
	public String deleteFavMovie(@PathVariable(name = "id") int id) {
		this.favMovieService.delete(id);
		return "redirect:/favmovie";
	}
	
	@GetMapping("/historyFilm")
	public String historyFilm(Model model, @RequestParam(name="page", defaultValue = "1") int page) {
		loadController.categoryShow(model);
		loadController.genresShow(model);
		Long idUser = loadController.getUserIdFromUserDetails();
		if(idUser > 0) {
			List<HistoryWatch> watch = (List<HistoryWatch>) historyWatchService.findByUser_watch(idUser);
			if(watch.size() <= 0) model.addAttribute("checkLogin", "Bạn chưa xem bộ phim nào");
			else model.addAttribute("listHistoryWatch", watch);
		} else model.addAttribute("checkLogin", "Bạn cần phải <a href='/login'>đăng nhập</a> để có thể sử dụng tính năng này");
		return "historyWatch";
	}
	
	@GetMapping("/historyFilm/delete/{id}")
	public String deleteHsFilm(@PathVariable(name="id") int id) {
		this.historyWatchService.delete(id);
		return "redirect:/historyFilm";
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

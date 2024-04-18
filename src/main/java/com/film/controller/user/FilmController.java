package com.film.controller.user;

import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.film.models.FavMovie;
import com.film.models.HistoryWatch;
import com.film.services.FavMovieService;
import com.film.services.FilmService;
import com.film.services.HistoryWatchService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FilmController {
	LoadController loadController = new LoadController();
	
	@Autowired
	private HistoryWatchService historyWatchService;
	@Autowired
	private FavMovieService favMovieService;
	@Autowired
	private FilmService filmService;
	
	private FilmController(LoadController loadController) {
		this.loadController = loadController;
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
		
		if (!slug.equals(request.getSession().getAttribute("currentSlug"))) {
			List<String> listSlug = filmService.findSlugFilm();
			for (String string : listSlug) {
				if(string.equals(slug)) {
					int view = filmService.findViewBySlug(slug);
					filmService.updateViewBySlug(string, view + 1);
					request.getSession().setAttribute("currentSlug", slug);
				}
			}
		}
		
		return "anime-watching";
	}
	
	@GetMapping("/detail-film")
	public String detailFilm(Model model, @RequestParam(name = "slug") String slug, HttpServletRequest request) {
		loadController.categoryShow(model);
		loadController.genresShow(model);
		Long idWatch = loadController.getUserIdFromUserDetails();
		if (idWatch != 0) {
			FavMovie returnFavMovie = favMovieService.findBySlugAndUser_fav(slug, idWatch.intValue());
    		if (returnFavMovie != null) model.addAttribute("checkMessageFav", "Đã có");
    		HistoryWatch watch = historyWatchService.findByUser_watchAndSlug(idWatch, slug);
    		model.addAttribute("history", watch);
		}
		request.getSession().setAttribute("currentSlug", "");
		return "anime-details";
	}
	
	@PostMapping("/detail-film")
	public ResponseEntity<String> detailFilmPost(Model model,			
			@RequestParam(name = "slug") String slug,
			@RequestParam(required = false, defaultValue = "") String name_movie,
            @RequestParam(required = false, defaultValue = "") String img_movie,
            @RequestParam(required = false, defaultValue = "") String episode,
            @RequestParam(required = false, defaultValue = "") String view_movie,
            @RequestParam(required = false, defaultValue = "") String category_movie) {
		
		Long idWatch = loadController.getUserIdFromUserDetails();
		String notification = "";
		if (idWatch.intValue() != 0) {			
			FavMovie favMovie = new FavMovie();
			favMovie.setUser(idWatch.intValue());
			favMovie.setEpisode(episode);
			favMovie.setImg_movie(img_movie);
			favMovie.setName_movie(name_movie);
			favMovie.setView_movie(view_movie);
			favMovie.setSlug(slug);
			favMovie.setCategory(category_movie);
    		FavMovie returnFavMovie = favMovieService.findBySlugAndUser_fav(slug, idWatch.intValue());    		
    		if (returnFavMovie == null) {
    			if (favMovieService.update(favMovie)) {
    				notification = "Thêm thành công";
    			}
    		} else {
    			favMovieService.delete(returnFavMovie.getId());
    			notification = "Xóa thành công"; 
    		}
		} else {
			notification = "Bạn cần đăng nhập";
		}
		HistoryWatch watch = historyWatchService.findByUser_watchAndSlug(idWatch, slug);		
		model.addAttribute("history", watch);
	    return ResponseEntity.ok(notification);
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
}

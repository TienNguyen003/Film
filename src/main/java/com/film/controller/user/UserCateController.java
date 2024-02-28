package com.film.controller.user;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.film.models.Category;
import com.film.models.CustomUserDetails;
import com.film.models.FavMovie;
import com.film.models.Genres;
import com.film.models.HistoryWatch;
import com.film.services.CategoryService;
import com.film.services.FavMovieService;
import com.film.services.GenresService;
import com.film.services.HistoryWatchService;

@Controller
public class UserCateController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private GenresService genresService;
	@Autowired
	private HistoryWatchService historyWatchService;
	@Autowired
	private FavMovieService favMovieService;
	
	@GetMapping("/")
	public String category(Model model) {
		categoryShow(model);
		genresShow(model);
		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		categoryShow(model);
		genresShow(model);
		return "login";
	}
	
	@GetMapping("/genres")
	public String categories(Model model) {
		categoryShow(model);
		genresShow(model);
		return "categorie";
	}
	
	@GetMapping("/categories")
	public String categorie(Model model) {
		categoryShow(model);
		genresShow(model);
		return "categories";
	}
	
	@GetMapping("/our-blog")
	public String categoryBlog(Model model) {
		categoryShow(model);
		genresShow(model);
		return "blog";
	}
	
	@GetMapping("/watch-film")
	public String watchFilm(Model model, @RequestParam(name = "slug") String slug, @RequestParam(name = "episodes", defaultValue = "1") int episodes) {
		categoryShow(model);
		genresShow(model);
		Long idWatch = getUserIdFromUserDetails();
		HistoryWatch watch = historyWatchService.findByUser_watchAndSlug(idWatch, slug);
		if(idWatch > 0) {
			HistoryWatch historyWatch = new HistoryWatch();
			historyWatch.setEpisode(episodes);
			historyWatch.setUser_watch(idWatch.intValue());
			historyWatch.setSlug(slug);
			if(watch != null) {historyWatch.setId(watch.getId());}
			historyWatchService.update(historyWatch);
		}
		return "anime-watching";
	}
	
	@GetMapping("/detail-film")
	public String detailFilm(Model model, @RequestParam(name = "slug") String slug) {
		categoryShow(model);
		genresShow(model);
		Long idWatch = getUserIdFromUserDetails();
		if (idWatch != 0) {
			FavMovie returnFavMovie = favMovieService.findBySlugAndUser_fav(slug, idWatch.intValue());
    		if (returnFavMovie != null) model.addAttribute("checkMessageFav", "Đã có");
		}
		HistoryWatch watch = historyWatchService.findByUser_watchAndSlug(idWatch, slug);
		model.addAttribute("history", watch);
		return "anime-details";
	}
	
	@PostMapping("/detail-film")
	public String detailFilmPost(Model model,
			@RequestParam(name = "slug") String slug,
			@RequestParam String name_movie,
            @RequestParam String img_movie,
            @RequestParam String episode,
            @RequestParam String view_movie) {
		categoryShow(model);
		genresShow(model);
		Long idWatch = getUserIdFromUserDetails();
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
		categoryShow(model);
		genresShow(model);
		return "searchFilm";
	}
	
	@GetMapping("/favmovie")
	public String favMovie(Model model, @RequestParam(name="page", defaultValue = "1") int page) {
		categoryShow(model);
		genresShow(model);
		int idUser = getUserIdFromUserDetails().intValue();
		if(idUser != 0) {
			Page<FavMovie> list = favMovieService.getPaginatedRecordsForUser(page-1, 24, idUser);
			model.addAttribute("listFavMovie", list);	
			model.addAttribute("totalPage", list.getTotalPages());
			model.addAttribute("currentPage", page);
		} else model.addAttribute("checkLogin", "Bạn cần phải <a href='/login'>đăng nhập</a> để có thể sử dụng tính năng này");
		return "favmovie";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		categoryShow(model);
		genresShow(model);
		return "signup";
	}
	
	private Model categoryShow(Model model) {
		List<Category> list = this.categoryService.getAll();
		model.addAttribute("list", list);
		return model;
	}
	
	private Model genresShow(Model model) {
		List<Genres> listGenres = this.genresService.getAll();
		model.addAttribute("listGenres", listGenres);
		return model;
	}
	
	private Long getUserIdFromUserDetails() {
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
}

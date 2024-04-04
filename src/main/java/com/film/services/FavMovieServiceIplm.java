package com.film.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.film.models.FavMovie;
import com.film.repository.FavMovieRepository;

@Service
public class FavMovieServiceIplm implements FavMovieService {
	@Autowired
	private FavMovieRepository favMovieRepository;
	
	@Override
	public List<FavMovie> findByUserFav(int id) {
		return this.favMovieRepository.findByUserFav(id);
	}

	@Override
	public Boolean update(FavMovie favMovie) {
		try {
			this.favMovieRepository.save(favMovie);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public FavMovie findBySlugAndUser_fav(String slug, int id) {
		return this.favMovieRepository.findBySlugAndUser_fav(slug, id);
	}

	@Override
	public Boolean delete(int id) {
		try {
			this.favMovieRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Page<FavMovie> getPaginatedRecordsForUser(int pageNumber, int pageSize, int id) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
	    return this.favMovieRepository.findMovieByUser(id, pageable);
	}

	@Override
	public List<String> findCategoryById(int user_fav) {
		return this.favMovieRepository.findCategoryById(user_fav);
	}

}

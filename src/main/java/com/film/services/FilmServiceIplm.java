package com.film.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.film.models.FilmModel;
import com.film.repository.FilmRepository;

@Service
public class FilmServiceIplm implements FilmService{
	@Autowired
	private FilmRepository filmRepository;

	@Override
	public List<FilmModel> getAll() {
		try {
			return this.filmRepository.findAll();
			
		} catch (Exception e) {
			System.out.println("lỗi");
		}
		return null;
	}

	@Override
	public Boolean save(FilmModel filmModel) {
		try {
			this.filmRepository.save(filmModel);
			return true;			
		} catch (Exception e) {
			System.out.println("lỗi");
		}
		return false;
	}

	@Override
	public Boolean delete(int id) {
		try {
			this.filmRepository.deleteById(id);
			return true;			
		} catch (Exception e) {
			System.out.println("Lỗi");
		}
		return false;
	}

	@Override
	public FilmModel getById(int id) {
		try {
			return this.filmRepository.findById(id).get();			
		} catch (Exception e) {
			System.out.println("lỗi");
		}
		return null;
	}

	@Override
	public List<FilmModel> findRandAll() {
		try {
			return this.filmRepository.findRandAll();			
		} catch (Exception e) {			
			System.out.println("lỗi");
		}
		return null;
	}

	@Override
	public List<FilmModel> findAllByView() {
		try {
			return this.filmRepository.findAllByView();
		} catch (Exception e) {
			System.out.println("Lỗi");
		}
		return null;
	}

	@Override
	public int findViewBySlug(String slug) {
		return this.filmRepository.findViewBySlug(slug);
	}

	@Override
	public List<String> findSlugFilm() {
		try {
			return this.filmRepository.findSlugFilm();
		} catch (Exception e) {
			System.out.println("Lỗi");
		}
		return null;
	}

	@Override
	public Boolean updateViewBySlug(String slug, int view) {
		try {
			this.filmRepository.updateViewBySlug(slug, view);
			return true;
		} catch (Exception e) {
			System.out.println("Lỗi");
		}
		return null;
	}

	@Override
	public List<FilmModel> findByTypeGenres(String type, String genres) {
		try {
			return this.filmRepository.findByTypeGenres(type, genres);
		} catch (Exception e) {
			System.out.println("Lỗi");
		}
		return null;
	}

}

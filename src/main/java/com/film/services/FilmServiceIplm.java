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
			// TODO: handle exception
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
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public FilmModel getById(int id) {
		try {
			return this.filmRepository.findById(id).get();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("lỗi");
		}
		return null;
	}

}

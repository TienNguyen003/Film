package com.film.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.film.models.Genres;
import com.film.repository.GenresRepository;

@Service
public class GenresServiceIplm implements GenresService{
	@Autowired
	private GenresRepository genresRepository;
	
	@Override
	public List<Genres> getAll() {
		// TODO Auto-generated method stub
		return this.genresRepository.findAll();
	}

	@Override
	public Boolean create(Genres genres) {
		// TODO Auto-generated method stub
		try {
			this.genresRepository.save(genres);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Genres findById(Integer id) {
		// TODO Auto-generated method stub
		return this.genresRepository.findById(id).get();
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		try {
			this.genresRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean update(Genres category) {
		// TODO Auto-generated method stub
		return null;
	}

}

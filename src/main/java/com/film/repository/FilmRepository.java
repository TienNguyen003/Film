package com.film.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.film.models.FilmModel;

public interface FilmRepository extends JpaRepository<FilmModel, Integer>{

}

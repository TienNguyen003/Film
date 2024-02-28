package com.film.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.film.models.Genres;

public interface GenresRepository extends JpaRepository<Genres, Integer>{

}

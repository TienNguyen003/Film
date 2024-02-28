package com.film.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.film.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}

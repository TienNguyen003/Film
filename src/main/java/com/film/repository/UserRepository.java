package com.film.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.film.models.UserModel;


public interface UserRepository extends JpaRepository<UserModel, Long>{
	UserModel findByUserName(String userName);
}

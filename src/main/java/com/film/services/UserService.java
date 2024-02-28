package com.film.services;

import com.film.models.UserModel;

public interface UserService {
	UserModel findByUserName(String userName);
}

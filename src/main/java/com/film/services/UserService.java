package com.film.services;

import com.film.models.UserModel;

public interface UserService {
	UserModel findByUserName(String userName);
	Boolean save(UserModel userModel);
	int findByUser(String name);
	int findByEmail(String email);
	Object[][] queryByPoint(int id);
	Boolean updateFullName(String display_name, int point, int id);
	Boolean updateMaxim(String maxim, int id);
}

package com.film.services;

import org.springframework.stereotype.Repository;

import com.film.models.UserModel;

@Repository
public interface UserService {
	UserModel findByUserName(String userName);
	Boolean save(UserModel userModel);
	int findByUser(String name);
	int findByEmail(String email);
	int findByEmailRtId(String email);
	Object[][] queryByPoint(int id);
	Boolean updateFullName(String display_name, int point, int id);
	Boolean updateMaxim(String maxim, int id);
	Boolean updateIsActivity(int isActivity, String lastActive, int id);
	Boolean updateAvatar(String img, int id);
}

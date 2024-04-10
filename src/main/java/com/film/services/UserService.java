package com.film.services;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.film.models.UserModel;

@Repository
public interface UserService {
	UserModel findByUserName(String userName);
	int findByUser(String name);
	int findByEmail(String email);
	int findByEmailRtId(String email);
	String findBadgesById(int id);
	Object[][] queryByPoint(int id);
	Object[][] queryAttendanceById(int id);
	Boolean save(UserModel userModel);
	Boolean updateFullName(String display_name, int point, int id);
	Boolean updateMaxim(String maxim, int id);
	Boolean updateIsActivity(int isActivity, String lastActive, int id);
	Boolean updateIsAttendance(String attendance_day, String attendance, int point, int id);
	Boolean updateAvatar(String img, int id);
}

package com.film.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.film.models.UserModel;
import com.film.repository.UserRepository;

@Service
public class UserServiceIplm implements UserService {

	@Autowired
	private UserRepository userRP;
	
	@Override
	public UserModel findByUserName(String userName) {
		return userRP.findByUserName(userName);
	}

	@Override
	public Boolean save(UserModel userModel) {
		try {
			this.userRP.save(userModel);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public int findByUser(String name) {
		return this.userRP.findByUser(name);
	}

	@Override
	public int findByEmail(String email) {
		return this.userRP.findByEmail(email);
	}

	@Override
	public Boolean updateFullName(String name, int point, int id) {
		try {
			this.userRP.updateFullName(name, point, id);
			return true;
		} catch (Exception e) {
			System.out.println("không được");
		}
		return false;
	}

	@Override
	public Object[][] queryByPoint(int id) {		
		return this.userRP.queryByPoint(id);
	}

	@Override
	public Boolean updateMaxim(String maxim, int id) {
		try {
			this.userRP.updateMaxim(maxim, id);
			return true;
		} catch (Exception e) {
			System.out.println("không được");
		}
		return false;
	}
	
}
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
		// TODO Auto-generated method stub
		return userRP.findByUserName(userName);
	}
	
}

package com.film.controller.user;

import java.util.Map;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.film.models.UserGoogle;

@RestController
public class LoginOauth2Controller {
	@GetMapping("/login-google")
	public String loginGoogle() {
		return "tine";
	}
	
	public UserGoogle toPerson(Map<String, Object> map) {
		if(map==null) return null;
		UserGoogle userGoogle = new UserGoogle();
		userGoogle.setEmail((String) map.get("email"));
		userGoogle.setName((String) map.get("name"));
		userGoogle.setPicture((String) map.get("picture"));
		return userGoogle;
	}
}

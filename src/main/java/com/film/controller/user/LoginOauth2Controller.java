package com.film.controller.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.film.models.CustomUserDetails;
import com.film.models.UserGoogle;
import com.film.models.UserModel;
import com.film.services.UserService;

@RestController
public class LoginOauth2Controller {
	@Autowired
	private UserService userService;

	@GetMapping("/login-google")
	public String currentUser(OAuth2AuthenticationToken auth2AuthenticationToken) {
//		if (auth2AuthenticationToken != null
//				&& auth2AuthenticationToken.getPrincipal() instanceof OAuth2AuthenticationToken) {
			Map<String, Object> userAttributes = auth2AuthenticationToken.getPrincipal().getAttributes();
			UserGoogle userGoogle = toPerson(userAttributes);
			System.out.println(userGoogle.getEmail());
//			UserModel userModel = new UserModel();
//			userModel.setEmail(userGoogle.getEmail());
//			userModel.setUserName(userGoogle.getName());
//			userModel.setEnabled(true);
//			userModel.setImg(userGoogle.getPicture());
//			LocalDate now = LocalDate.now();
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//			userModel.setCreateAt(now.format(formatter));
//			if (userService.findByEmail(userModel.getEmail()) < 0)
//				userService.save(userModel);
//			else
//				userModel.setId((long) userService.findByEmailRtId(userModel.getEmail()));
//			System.out.println(userModel.getId());
//		} else if (auth2AuthenticationToken != null
//				&& auth2AuthenticationToken.getPrincipal() instanceof CustomUserDetails) {
//			CustomUserDetails customUser = (CustomUserDetails) auth2AuthenticationToken.getPrincipal();
//
//		}
		return "";
	}

	private UserGoogle toPerson(Map<String, Object> map) {
		if (map == null)
			return null;
		UserGoogle userGoogle = new UserGoogle();
		userGoogle.setEmail((String) map.get("email"));
		userGoogle.setName((String) map.get("name"));
		userGoogle.setPicture((String) map.get("picture"));
		userGoogle.setEmail_verified((Boolean) map.get("email_verified"));
		userGoogle.setFamily_name((String) map.get("family_name"));
		return userGoogle;
	}
}

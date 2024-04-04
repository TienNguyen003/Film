package com.film.controller.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.film.models.UserModel;
import com.film.services.UserService;

@RestController
public class LoginOauth2Controller {
	@Autowired
	private UserService userService;

	@GetMapping("/login-google")
	public String currentUser(OAuth2AuthenticationToken auth2AuthenticationToken) {
		if (auth2AuthenticationToken != null
				&& auth2AuthenticationToken.getPrincipal() instanceof OAuth2AuthenticationToken) {
			Map<String, Object> userAttributes = auth2AuthenticationToken.getPrincipal().getAttributes();
			UserModel userGoogle = toPerson(userAttributes);
			System.out.println(userGoogle.getEmail());
			UserModel userModel = new UserModel();
			userModel.setEmail(userGoogle.getEmail());
			userModel.setUserName(userGoogle.getUserName());
			userModel.setEnabled(true);
			userModel.setImg(userGoogle.getImg());
			LocalDate now = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			userModel.setCreateAt(now.format(formatter));
			if (userService.findByEmail(userModel.getEmail()) < 0)
				//userService.save(userModel);
				System.out.println("");
			else
				userModel.setId((long) userService.findByEmailRtId(userModel.getEmail()));
		} 
		return "";
	}

	private UserModel toPerson(Map<String, Object> map) {
		if (map == null)
			return null;
		UserModel userGoogle = new UserModel();
		userGoogle.setEmail((String) map.get("email"));
		userGoogle.setUserName((String) map.get("name"));
		userGoogle.setImg((String) map.get("picture"));
		userGoogle.setEnabled((Boolean) map.get("email_verified"));
		return userGoogle;
	}
}

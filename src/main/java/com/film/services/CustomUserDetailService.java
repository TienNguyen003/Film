package com.film.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.film.models.CustomUserDetails;
import com.film.models.UserModel;
import com.film.models.User_Role;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserModel user = userService.findByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("Sai");
		}
		Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
		Set<User_Role> roles = user.getUserRoles();
		
		for(User_Role userRole : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
		}
		return new CustomUserDetails(user, grantedAuthorities, userService);
	}

}

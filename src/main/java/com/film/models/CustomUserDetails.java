package com.film.models;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.film.services.UserService;

public class CustomUserDetails implements UserDetails{
	@Autowired
	private UserService userService;
	
	private UserModel user;
	private Collection<? extends GrantedAuthority> authorities;
	String userImg, fullName, maxim, createAt, email, attendance_day;
	int point, isActivity, crystal;	
	
	public CustomUserDetails() {
		// TODO Auto-generated constructor stub
	}
	
	public CustomUserDetails(UserModel user, Collection<? extends GrantedAuthority> authorities, UserService service) {
		super();
		this.user = user;
		this.authorities = authorities;
		this.userService = service;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassWord();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserName();
	}
	
	public Long getId() {
		return user.getId();
	}
	
	public String getImg() {
		return user.getImg();
	}
	
	public void setImg(String img) {
		this.userImg = img;
	}
	
	public String getFullName() {
		return user.getFullName();
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMaxim() {
		return user.getMaxim();
	}

	public void setMaxim(String maxim) {
		this.maxim = maxim;
	}

	public String getCreateAt() {
		return user.getCreateAt();
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public int getPoint() {
		return user.getPoint();
	}

	public void setPoint(int point) {
		this.point = point;
	}
	

	public String getEmail() {
		return user.getEmail();
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIsActivity() {
		return user.getIsActivity();
	}

	public void setIsActivity(int isActivity) {
		this.isActivity = isActivity;
	}	

	public String getAttendance_day() {
		return user.getAttendance_day();
	}

	public void setAttendance_day(String attendance_day) {
		this.attendance_day = attendance_day;
	}

	public int getCrystal() {
		return user.getCrystal();
	}

	public void setCrystal(int crystal) {
		this.crystal = crystal;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		boolean isEnabled = false;
		int enabled = userService.getEnabledById((user.getId()).intValue());
		if(enabled == 1) isEnabled = true;
		return isEnabled;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

}

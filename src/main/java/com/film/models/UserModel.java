package com.film.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserModel {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "username")
	private String userName;
	@Column(name = "password")
	private String passWord;
	@Column(name = "enabled")
	private Boolean enabled;
	@Column(name = "fullname")
	private String fullName;
	@Column(name = "gender")
	private Boolean gender;
	@Column(name = "birthday")
	private String birthday;
	@Column(name = "email")
	private String email;
	@Column(name = "maxim")
	private String maxim;
	@Column(name = "image")
	private String img;
	@Column(name = "create_at")
	private String createAt;
	@Column(name = "point")
	private int point;
	@Column(name = "is_activity")
	private int is_activity;
	@Column(name = "last_active")
	private String last_active;
	@Column(name = "attendance")
	private String attendance;
	@Column(name = "users_badges")
	private String users_badges;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<User_Role> userRoles;

	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserModel(Long id, String userName, String passWord, Boolean enabled, String fullName, Boolean gender,
			String birthday, String email, String maxim, String img, String createAt, int point, int is_activity,
			String last_active, String attendance, String users_badges, Set<User_Role> userRoles) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.enabled = enabled;
		this.fullName = fullName;
		this.gender = gender;
		this.birthday = birthday;
		this.email = email;
		this.maxim = maxim;
		this.img = img;
		this.createAt = createAt;
		this.point = point;
		this.is_activity = is_activity;
		this.last_active = last_active;
		this.attendance = attendance;
		this.users_badges = users_badges;
		this.userRoles = userRoles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMaxim() {
		return maxim;
	}

	public void setMaxim(String maxim) {
		this.maxim = maxim;
	}

	public Set<User_Role> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<User_Role> userRoles) {
		this.userRoles = userRoles;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getIsActivity() {
		return is_activity;
	}

	public void setIsActivity(int isActivity) {
		this.is_activity = isActivity;
	}

	public String getLastActive() {
		return last_active;
	}

	public void setLastActive(String lastActive) {
		this.last_active = lastActive;
	}

	public int getIs_activity() {
		return is_activity;
	}

	public void setIs_activity(int is_activity) {
		this.is_activity = is_activity;
	}

	public String getLast_active() {
		return last_active;
	}

	public void setLast_active(String last_active) {
		this.last_active = last_active;
	}

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}

	public String getUsers_badges() {
		return users_badges;
	}

	public void setUsers_badges(String users_badges) {
		this.users_badges = users_badges;
	}	
	
}
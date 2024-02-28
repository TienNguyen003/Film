package com.film.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "role")
	private Set<User_Role> roleUsers;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(Long id, String name, Set<User_Role> roleUsers) {
		super();
		this.id = id;
		this.name = name;
		this.roleUsers = roleUsers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User_Role> getRoleUsers() {
		return roleUsers;
	}

	public void setRoleUsers(Set<User_Role> roleUsers) {
		this.roleUsers = roleUsers;
	}
}

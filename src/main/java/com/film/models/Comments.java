package com.film.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Comments {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "slugFilm")
	private String slugFilm;
	@Column(name = "createAt")
	private Date createAt;
	@Column(name = "nameFilm")
	private String nameFilm;
	@Column(name = "idUser")
	private int idUser;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "image")
	private String image;
	@Column(name = "emailVerified")
	private int emailVerified;
	
	public Comments() {
		super();
	}

	public Comments(int id, String slugFilm, Date createAt, String nameFilm, int idUser, String name, String email,
			String image, int emailVerified) {
		super();
		this.id = id;
		this.slugFilm = slugFilm;
		this.createAt = createAt;
		this.nameFilm = nameFilm;
		this.idUser = idUser;
		this.name = name;
		this.email = email;
		this.image = image;
		this.emailVerified = emailVerified;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSlugFilm() {
		return slugFilm;
	}

	public void setSlugFilm(String slugFilm) {
		this.slugFilm = slugFilm;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getNameFilm() {
		return nameFilm;
	}

	public void setNameFilm(String nameFilm) {
		this.nameFilm = nameFilm;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(int emailVerified) {
		this.emailVerified = emailVerified;
	}	
}

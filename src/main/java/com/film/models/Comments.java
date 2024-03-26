package com.film.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comments {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "slug_film")
	private String slugFilm;
	@Column(name = "create_at")
	private String createAt;
	@Column(name = "id_user")
	private int idUser;
	@Column(name = "name_user")
	private String name;
	@Column(name = "image")
	private String image;
	@Column(name = "content_comment")
	private String content;
	@Column(name = "edit_comment")
	private String edit_comment;
	
	public Comments() {
		super();
	}

	public Comments(int id, String slugFilm, String createAt, int idUser, String name, String image, String content, String edit_comment) {
		super();
		this.id = id;
		this.slugFilm = slugFilm;
		this.createAt = createAt;
		this.idUser = idUser;
		this.name = name;
		this.image = image;
		this.content = content;
		this.edit_comment = edit_comment;
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

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEdit_comment() {
		return edit_comment;
	}

	public void setEdit_comment(String edit_comment) {
		this.edit_comment = edit_comment;
	}	
}

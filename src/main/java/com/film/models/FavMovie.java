package com.film.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "favmovie")
public class FavMovie {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "slug")
	private String slug;
	@Column(name = "user_fav")
	private int user;
	@Column(name = "name_movie")
	private String name_movie;
	@Column(name = "img_movie")
	private String img_movie;
	@Column(name = "view_movie")
	private String view_movie;
	@Column(name = "episode")
	private String episode;	
	@Column(name = "category")
	private String category;	
	
	public FavMovie(int id, String slug, int user_fav, String name_movie, String img_movie, String view_movie,
			String episode, String category) {
		super();
		this.id = id;
		this.slug = slug;
		this.user = user_fav;
		this.name_movie = name_movie;
		this.img_movie = img_movie;
		this.view_movie = view_movie;
		this.episode = episode;
		this.category = category;
	}

	public FavMovie() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public String getName_movie() {
		return name_movie;
	}

	public void setName_movie(String name_movie) {
		this.name_movie = name_movie;
	}

	public String getImg_movie() {
		return img_movie;
	}

	public void setImg_movie(String img_movie) {
		this.img_movie = img_movie;
	}

	public String getView_movie() {
		return view_movie;
	}

	public void setView_movie(String view_movie) {
		this.view_movie = view_movie;
	}

	public String getEpisode() {
		return episode;
	}

	public void setEpisode(String episode) {
		this.episode = episode;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}

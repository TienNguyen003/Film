package com.film.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "history_watch")
public class HistoryWatch {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "episode")
	private int episode;
	@Column(name = "user_watch")
	private int user_watch;
	@Column(name = "slug")
	private String slug;
	@Column(name = "name_movie")
	private String name_movie;
	@Column(name = "img_movie")
	private String img_movie;
	@Column(name = "view_movie")
	private int view_movie;
	
	public HistoryWatch() {
		super();
	}	

	public HistoryWatch(Integer id, int episode, int user_watch, String slug, String name_movie, String img_movie,
			int view_movie) {
		super();
		this.id = id;
		this.episode = episode;
		this.user_watch = user_watch;
		this.slug = slug;
		this.name_movie = name_movie;
		this.img_movie = img_movie;
		this.view_movie = view_movie;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}

	public int getUser_watch() {
		return user_watch;
	}

	public void setUser_watch(int user_watch) {
		this.user_watch = user_watch;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
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

	public int getView_movie() {
		return view_movie;
	}

	public void setView_movie(int view_movie) {
		this.view_movie = view_movie;
	}		
}

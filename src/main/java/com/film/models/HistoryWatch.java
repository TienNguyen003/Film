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
	
	public HistoryWatch() {
		super();
	}

	public HistoryWatch(Integer id, int episode, int user_watch, String slug) {
		super();
		this.id = id;
		this.episode = episode;
		this.user_watch = user_watch;
		this.slug = slug;
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
}

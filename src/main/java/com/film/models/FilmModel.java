package com.film.models;

import java.util.ArrayList;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class FilmModel {
	@Column(name = "created")
	private String created;
	@Column(name = "modified")
	private String modified;
    @Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "slug")
    private String slug;
    @Column(name = "origin_name")
    private String origin_name;
    @Column(name = "content")
    private String content;
    @Column(name = "type")
    private String type;
    @Column(name = "status")
    private String status;
    @Column(name = "thumb_url")
    private String thumb_url;
    @Column(name = "poster_url")
    private String poster_url;
    @Column(name = "trailer_url")
    private String trailer_url;
    @Column(name = "time")
    private String time;
    @Column(name = "episode_current")
    private String episode_current;
    @Column(name = "episode_total")
    private String episode_total;
    @Column(name = "quality")
    private String quality;
    @Column(name = "year")
    private int year;
    @Column(name = "view")
    private int view;
    @Column(name = "category")
    private Set<Category> category;
    @Column(name = "genres")
    private ArrayList<Genres> genres;
    @Column(name = "country")
    private String country;	

	public FilmModel(String created, String modified, String id, String name, String slug, String origin_name,
			String content, String type, String status, String thumb_url, String poster_url, String trailer_url,
			String time, String episode_current, String episode_total, String quality, int year, int view,
			Set<Category> category, ArrayList<Genres> genres, String country) {
		super();
		this.created = created;
		this.modified = modified;
		this.id = id;
		this.name = name;
		this.slug = slug;
		this.origin_name = origin_name;
		this.content = content;
		this.type = type;
		this.status = status;
		this.thumb_url = thumb_url;
		this.poster_url = poster_url;
		this.trailer_url = trailer_url;
		this.time = time;
		this.episode_current = episode_current;
		this.episode_total = episode_total;
		this.quality = quality;
		this.year = year;
		this.view = view;
		this.category = category;
		this.genres = genres;
		this.country = country;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getOrigin_name() {
		return origin_name;
	}

	public void setOrigin_name(String origin_name) {
		this.origin_name = origin_name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getThumb_url() {
		return thumb_url;
	}

	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}

	public String getPoster_url() {
		return poster_url;
	}

	public void setPoster_url(String poster_url) {
		this.poster_url = poster_url;
	}

	public String getTrailer_url() {
		return trailer_url;
	}

	public void setTrailer_url(String trailer_url) {
		this.trailer_url = trailer_url;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getEpisode_current() {
		return episode_current;
	}

	public void setEpisode_current(String episode_current) {
		this.episode_current = episode_current;
	}

	public String getEpisode_total() {
		return episode_total;
	}

	public void setEpisode_total(String episode_total) {
		this.episode_total = episode_total;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public Set<Category> getCategory() {
		return category;
	}

	public void setCategory(Set<Category> category) {
		this.category = category;
	}

	public ArrayList<Genres> getGenres() {
		return genres;
	}

	public void setGenres(ArrayList<Genres> genres) {
		this.genres = genres;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}    
}

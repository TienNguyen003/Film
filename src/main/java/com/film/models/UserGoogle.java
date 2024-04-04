package com.film.models;

public class UserGoogle {
	public boolean email_verified;
    public String picture;
    public String name;
    public String family_name;
    public String email;
    
	public UserGoogle() {
		super();
	}

	public UserGoogle(boolean email_verified, String picture, String name, String family_name, String email) {
		super();
		this.email_verified = email_verified;
		this.picture = picture;
		this.name = name;
		this.family_name = family_name;
		this.email = email;
	}

	public boolean isEmail_verified() {
		return email_verified;
	}

	public void setEmail_verified(boolean email_verified) {
		this.email_verified = email_verified;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamily_name() {
		return family_name;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}  
	
	
}

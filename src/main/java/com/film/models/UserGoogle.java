package com.film.models;

import java.util.ArrayList;


public class UserGoogle {
	public String at_hash;
    public String sub;
    public boolean email_verified;
    public String iss;
    public String given_name;
    public String locale;
    public String nonce;
    public String picture;
    public ArrayList<String> aud;
    public String azp;
    public String name;
    public String exp;
    public String family_name;
    public String iat;
    public String email;
    
	public UserGoogle() {
		super();
	}

	public UserGoogle(String at_hash, String sub, boolean email_verified, String iss, String given_name, String locale,
			String nonce, String picture, ArrayList<String> aud, String azp, String name, String exp, String family_name,
			String iat, String email) {
		super();
		this.at_hash = at_hash;
		this.sub = sub;
		this.email_verified = email_verified;
		this.iss = iss;
		this.given_name = given_name;
		this.locale = locale;
		this.nonce = nonce;
		this.picture = picture;
		this.aud = aud;
		this.azp = azp;
		this.name = name;
		this.exp = exp;
		this.family_name = family_name;
		this.iat = iat;
		this.email = email;
	}

	public String getAt_hash() {
		return at_hash;
	}

	public void setAt_hash(String at_hash) {
		this.at_hash = at_hash;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public boolean isEmail_verified() {
		return email_verified;
	}

	public void setEmail_verified(boolean email_verified) {
		this.email_verified = email_verified;
	}

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	public String getGiven_name() {
		return given_name;
	}

	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public ArrayList<String> getAud() {
		return aud;
	}

	public void setAud(ArrayList<String> aud) {
		this.aud = aud;
	}

	public String getAzp() {
		return azp;
	}

	public void setAzp(String azp) {
		this.azp = azp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getFamily_name() {
		return family_name;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	public String getIat() {
		return iat;
	}

	public void setIat(String iat) {
		this.iat = iat;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}    
}

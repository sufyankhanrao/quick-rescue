package com.gr.qrapi.core.model;

import java.io.Serializable;
import java.util.List;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String emailDomain;
	private String timeZoneCity;
	private String username;
	private String password;
	private List<Contact> contacts;
	private List<AlertProfile> profiles;

	public Account() {
	}
	

	public Account(String name, String emailDomain, String timeZoneCity, String username, String password) {
		super();
		this.name = name;
		this.emailDomain = emailDomain;
		this.timeZoneCity = timeZoneCity;
		this.username=username;
		this.password=password;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailDomain() {
		return emailDomain;
	}

	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}

	public String getTimeZoneCity() {
		return timeZoneCity;
	}

	public void setTimeZoneCity(String timeZoneCity) {
		this.timeZoneCity = timeZoneCity;
	}
	

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<AlertProfile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<AlertProfile> profiles) {
		this.profiles = profiles;
	}

}

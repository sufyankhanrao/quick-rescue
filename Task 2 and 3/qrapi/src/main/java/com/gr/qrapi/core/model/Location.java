package com.gr.qrapi.core.model;

import java.io.Serializable;

public class Location implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String city;
	private String country;
	public Location() {
	}

	public Location(String city, String country) {
		super();
		this.city = city;
		this.country = country;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	


}

package com.gr.qrapi.core.model;

import java.io.Serializable;

public class AlertProfile implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Location location;

	public AlertProfile() {
	}

	public AlertProfile(String name, Location location) {
		super();
		this.name = name;
		this.location = location;
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}

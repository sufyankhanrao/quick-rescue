package com.gr.qrapi.core.model;

import java.io.Serializable;
import java.util.List;

public class AlertProfile implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private List<Location> locations;

	public AlertProfile() {
	}

	public AlertProfile(String name, List<Location> locations) {
		super();
		this.name = name;
		this.locations = locations;
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

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}


}

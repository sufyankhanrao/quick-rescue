package com.gr.assignments.pojorepository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "Address")
public class Address {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "Street")
	private String street;
	@Column(name = "City")
	private String city;

	@Column(name = "State")
	private String state;

	@Column(name = "Country")
	private String country;

	@OneToOne()
	@JoinColumn(name = "Contact_ID")
	private Contact contact;

	public Address() {
	}

	public Address(String street, String city, String state, String country) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
}

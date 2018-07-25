package com.gr.assignments.pojorepository;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Name")
	private String name;

	@Column(name = "Email_Domain")
	private String emailDomain;

	@Column(name = "Timezone_City")
	private String timezoneCity;

	@OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
	private Collection<Contact> contactList = new ArrayList<Contact>();

	public Account() {
	}

	public Account(String name, String emailDomain, String timezoneCity) {
		super();
		this.name = name;
		this.emailDomain = emailDomain;
		this.timezoneCity = timezoneCity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getTimezoneCity() {
		return timezoneCity;
	}

	public void setTimezoneCity(String timezoneCity) {
		this.timezoneCity = timezoneCity;
	}

	public Collection<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(Collection<Contact> contactList) {
		this.contactList = contactList;
	}

}

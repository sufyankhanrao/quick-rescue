package com.gr.assignments.DAOLayer;

import com.gr.assignments.pojorepository.Contact;

public class ContactObjMaker {
	private Contact contact;
	private int AccountID;

	public ContactObjMaker(Contact contact, int accountID) {
		super();
		this.contact = contact;
		AccountID = accountID;
	}

	public Contact getContact() {
		return contact;
	}

	public int getAccountID() {
		return AccountID;
	}
}

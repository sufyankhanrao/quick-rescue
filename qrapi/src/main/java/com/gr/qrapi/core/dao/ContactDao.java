package com.gr.qrapi.core.dao;

import java.util.List;
import com.gr.common.dao.GenericDao;
import com.gr.qrapi.core.model.Contact;

public interface ContactDao extends GenericDao<Contact, Integer> {
	String addContact(int accountID, Contact contact);

	Contact updateContact(int contactID, Contact contact);

	Contact deleteContact(int contactID);

	List<Contact> getAllContacts();
}

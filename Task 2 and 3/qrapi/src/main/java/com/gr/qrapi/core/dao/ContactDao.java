package com.gr.qrapi.core.dao;

import java.util.List;

import com.gr.common.dao.GenericDao;
import com.gr.qrapi.core.model.Address;
import com.gr.qrapi.core.model.Contact;

public interface ContactDao extends GenericDao<Contact, Integer> {
	String addContact(int accountID, Contact contact);

	String updateContact(int contactID, Contact contact);

	String deleteContact(int contactID);

	List<Contact> getAllContactsofAccount(int accountID);
	
	List<Address> getAllAddressesofContacts(int accountID);

	Contact searchContactByID(int contactID);
}

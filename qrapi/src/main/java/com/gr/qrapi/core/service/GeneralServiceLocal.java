package com.gr.qrapi.core.service;

import java.util.List;

import javax.ejb.Local;

import com.gr.qrapi.core.model.Account;
import com.gr.qrapi.core.model.AlertProfile;
import com.gr.qrapi.core.model.Contact;

@Local
public interface GeneralServiceLocal {
	String addAccount(Account account);
	Account updateAccount(int accountID,Account account);
	Account deleteAccount(int accountID);
	List<Account> getAllAccounts();
	
	
	String addProfile(int accountID,AlertProfile alertProfile);
	AlertProfile updateProfile(int profileID,AlertProfile alertProfile);
	AlertProfile deleteProfile(int profileID);
	List<AlertProfile> getAllProfiles();
	
	String addContact(int accountID,Contact contact);
	Contact updateContact(int contactID,Contact contact);
	Contact deleteContact(int contactID);
	List<Contact> getAllContacts();
}

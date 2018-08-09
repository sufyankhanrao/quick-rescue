package com.gr.qrapi.core.service;

import java.util.List;

import javax.ejb.Local;

import com.gr.qrapi.core.model.Account;
import com.gr.qrapi.core.model.Address;
import com.gr.qrapi.core.model.AlertProfile;
import com.gr.qrapi.core.model.Contact;

@Local
public interface GeneralServiceLocal {
	String addAccount(Account account);

	String updateAccount(int accountID, Account account);

	String deleteAccount(int accountID);

	List<Account> getAllAccounts();

	Account searchAccountByID(int accountID);

	String getLogin(String username, String password);

	String addProfile(int accountID, AlertProfile alertProfile);

	String updateProfile(int profileID, AlertProfile alertProfile);

	String deleteProfile(int profileID);

	List<AlertProfile> getAllProfiles();

	AlertProfile searchProfileByID(int alertProfileID);
	
	List<AlertProfile> getProfilesByAccountID(int accountID);

	String addContact(int accountID, Contact contact);

	String updateContact(int contactID, Contact contact);

	String deleteContact(int contactID);

	List<Contact> getAllContactsofAccount(int accountID);
	
	List<Address> getAllAddressesofContacts(int accountID);

	Contact searchContactByID(int contactID);
}

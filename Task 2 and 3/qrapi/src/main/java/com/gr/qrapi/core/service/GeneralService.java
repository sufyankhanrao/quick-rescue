package com.gr.qrapi.core.service;

import java.util.List;

import javax.ejb.Stateless;

import com.gr.common.service.ServiceManager;
import com.gr.qrapi.core.dao.AccountDaoHibernateImpl;
import com.gr.qrapi.core.dao.AlertProfileDaoHibernateImpl;
import com.gr.qrapi.core.dao.ContactDaoHibernateImpl;
import com.gr.qrapi.core.model.Account;
import com.gr.qrapi.core.model.Address;
import com.gr.qrapi.core.model.AlertProfile;
import com.gr.qrapi.core.model.Contact;

@Stateless
public class GeneralService implements GeneralServiceLocal {

	public static GeneralServiceLocal getService() {
		return (GeneralServiceLocal) ServiceManager.getService(GeneralServiceLocal.class);
	}

	// Account CRUD Operations starts here

	@Override
	public List<Account> getAllAccounts() {
		return AccountDaoHibernateImpl.getDao().getAllAccounts();
	}

	@Override
	public String addAccount(Account account) {
		return AccountDaoHibernateImpl.getDao().addAccount(account);
	}

	@Override
	public String updateAccount(int accountID, Account account) {
		return AccountDaoHibernateImpl.getDao().updateAccount(accountID, account);
	}

	@Override
	public String deleteAccount(int accountID) {
		return AccountDaoHibernateImpl.getDao().deleteAccount(accountID);
	}
	
	@Override
	public Account searchAccountByID(int accountID) {
		return AccountDaoHibernateImpl.getDao().searchAccountByID(accountID);
	}

	@Override
	public String getLogin(String username, String password) {
		return AccountDaoHibernateImpl.getDao().getLogin(username, password);
	}

	// Alert Profile CRUD Operations starts here

	@Override
	public String addProfile(int accountID, AlertProfile alertProfile) {
		return AlertProfileDaoHibernateImpl.getDao().addProfile(accountID, alertProfile);
	}

	@Override
	public String updateProfile(int profileID, AlertProfile alertProfile) {
		return AlertProfileDaoHibernateImpl.getDao().updateProfile(profileID, alertProfile);
	}

	@Override
	public String deleteProfile(int profileID) {
		return AlertProfileDaoHibernateImpl.getDao().deleteProfile(profileID);
	}

	@Override
	public List<AlertProfile> getAllProfiles() {
		return AlertProfileDaoHibernateImpl.getDao().getAllProfiles();
	}
	
	@Override
	public AlertProfile searchProfileByID(int alertProfileID) {
		return AlertProfileDaoHibernateImpl.getDao().searchProfileByID(alertProfileID);
	}

	@Override
	public List<AlertProfile> getProfilesByAccountID(int accountID) {
		return AlertProfileDaoHibernateImpl.getDao().getProfilesByAccountID(accountID);
	}

	// Contact CRUD Operations starts here

	@Override
	public String addContact(int accountID, Contact contact) {
		return ContactDaoHibernateImpl.getDao().addContact(accountID, contact);
	}

	@Override
	public String updateContact(int contactID, Contact contact) {
		return ContactDaoHibernateImpl.getDao().updateContact(contactID, contact);
	}

	@Override
	public String deleteContact(int contactID) {
		return ContactDaoHibernateImpl.getDao().deleteContact(contactID);
	}

	@Override
	public List<Contact> getAllContactsofAccount(int accountID) {
		return ContactDaoHibernateImpl.getDao().getAllContactsofAccount(accountID);
	}
	
	@Override
	public Contact searchContactByID(int contactID) {
		return ContactDaoHibernateImpl.getDao().searchContactByID(contactID);
	}
	
	@Override
	public List<Address> getAllAddressesofContacts(int accountID) {
		return ContactDaoHibernateImpl.getDao().getAllAddressesofContacts(accountID);
	}

}

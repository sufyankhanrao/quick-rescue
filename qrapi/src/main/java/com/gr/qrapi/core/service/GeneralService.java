package com.gr.qrapi.core.service;

import java.util.List;
import javax.ejb.Stateless;
import com.gr.common.service.ServiceManager;
import com.gr.qrapi.core.dao.AccountDaoHibernateImpl;
import com.gr.qrapi.core.dao.AlertProfileDaoHibernateImpl;
import com.gr.qrapi.core.dao.ContactDaoHibernateImpl;
import com.gr.qrapi.core.model.Account;
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
	public Account updateAccount(int accountID, Account account) {
		return AccountDaoHibernateImpl.getDao().updateAccount(accountID, account);
	}

	@Override
	public Account deleteAccount(int accountID) {
		return AccountDaoHibernateImpl.getDao().deleteAccount(accountID);
	}

	// Alert Profile CRUD Operations starts here

	@Override
	public String addProfile(int accountID, AlertProfile alertProfile) {
		return AlertProfileDaoHibernateImpl.getDao().addProfile(accountID, alertProfile);
	}

	@Override
	public AlertProfile updateProfile(int profileID, AlertProfile alertProfile) {
		return AlertProfileDaoHibernateImpl.getDao().updateProfile(profileID, alertProfile);
	}

	@Override
	public AlertProfile deleteProfile(int profileID) {
		return AlertProfileDaoHibernateImpl.getDao().deleteProfile(profileID);
	}

	@Override
	public List<AlertProfile> getAllProfiles() {
		return AlertProfileDaoHibernateImpl.getDao().getAllProfiles();
	}

	// Contact CRUD Operations starts here

	@Override
	public String addContact(int accountID, Contact contact) {
		return ContactDaoHibernateImpl.getDao().addContact(accountID, contact);
	}

	@Override
	public Contact updateContact(int contactID, Contact contact) {
		return ContactDaoHibernateImpl.getDao().updateContact(contactID, contact);
	}

	@Override
	public Contact deleteContact(int contactID) {
		return ContactDaoHibernateImpl.getDao().deleteContact(contactID);
	}

	@Override
	public List<Contact> getAllContacts() {
		return ContactDaoHibernateImpl.getDao().getAllContacts();
	}

}

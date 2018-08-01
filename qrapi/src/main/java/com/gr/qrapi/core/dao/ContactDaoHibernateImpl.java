package com.gr.qrapi.core.dao;

import java.util.List;

import com.gr.common.dao.AbstractHibernateDao;
import com.gr.common.dao.DaoManager;
import com.gr.qrapi.core.model.Contact;

public class ContactDaoHibernateImpl extends AbstractHibernateDao<Contact, Integer> implements ContactDao {

	public static ContactDao getDao() {
		return DaoManager.getInstance().getDao(ContactDao.class);
	}

	@Override
	public String addContact(int accountID, Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact updateContact(int contactID, Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact deleteContact(int contactID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contact> getAllContacts() {
		// TODO Auto-generated method stub
		return null;
	}

}

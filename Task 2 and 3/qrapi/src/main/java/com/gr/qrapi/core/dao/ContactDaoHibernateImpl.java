package com.gr.qrapi.core.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import com.gr.common.dao.AbstractHibernateDao;
import com.gr.common.dao.DaoException;
import com.gr.common.dao.DaoManager;
import com.gr.qrapi.core.model.Account;
import com.gr.qrapi.core.model.Address;
import com.gr.qrapi.core.model.Contact;

public class ContactDaoHibernateImpl extends AbstractHibernateDao<Contact, Integer> implements ContactDao {

	public static ContactDao getDao() {
		return DaoManager.getInstance().getDao(ContactDao.class);
	}

	@Override
	public String addContact(int accountID, Contact contact) {
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			Account retrievedAccount = (Account) session.get(Account.class, accountID);
			if (retrievedAccount == null) {
				if (session.isOpen()) {
					session.close();
				}
				return "{\"output\":\"failure\"}";
			}
			retrievedAccount.getContacts().add(contact);
			System.out.println(retrievedAccount.getId());
			System.out.println(contact.getFirstName());
			session.saveOrUpdate(retrievedAccount);
			session.save(contact);
			session.getTransaction().commit();
		} catch (HibernateException hbe) {
			session.getTransaction().rollback();
			hbe.printStackTrace();
			return "{\"output\":\"failure\"}";
		} finally {
			if (session != null)
				if (session.isOpen()) {
					session.close();
				}
		}
		return "{\"output\":\"success\"}";
	}

	@Override
	public String updateContact(int contactID, Contact contact) {
		Contact retrievedContact = null;
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			retrievedContact = (Contact) session.get(Contact.class, contactID);
			if (retrievedContact == null) {
				if (session.isOpen()) {
					session.close();
				}
				return "{\"output\":\"failure\"}";
			}
			Query query = session.createQuery(
					"update Address set Street = :street, City = :city, State = :state, Country = :country  where Id = "
							+ retrievedContact.getAddress().getId());
			query.setParameter("street", contact.getAddress().getStreet());
			query.setParameter("city", contact.getAddress().getCity());
			query.setParameter("state", contact.getAddress().getState());
			query.setParameter("country", contact.getAddress().getCountry());
			query.executeUpdate();
			query = session.createQuery(
					"update Contact set FName = :firstName, LName = :lastName, EmailID = :emailID, Gender = :gender, PhoneNo = :phoneNo, Status = :status  where Id = "
							+ contactID);
			query.setParameter("firstName", contact.getFirstName());
			query.setParameter("lastName", contact.getLastName());
			query.setParameter("emailID", contact.getEmailID());
			query.setParameter("gender", contact.getGender());
			query.setParameter("phoneNo", contact.getPhoneNo());
			query.setParameter("status", contact.getStatus());
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Account Update Op:Hibernate is unable to communicate with database");
			return "{\"output\":\"failure\"}";
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return "{\"output\":\"success\"}";
	}

	@Override
	public String deleteContact(int contactID) {
		Contact retrievedContact = null;
		Session session = null;
		Query query;
		try {
			session = getSession();
			session.beginTransaction();
			retrievedContact = (Contact) session.get(Contact.class, contactID);
			if (retrievedContact == null) {
				if (session.isOpen()) {
					session.close();
				}
				System.out.println("Contact Delete Op: Associated Account ID not Found!");
				return "{\"output\":\"failure\"}";
			}
			query = session.createQuery("delete Address where Id = " + retrievedContact.getAddress().getId());
			query.executeUpdate();
			query = session.createQuery("delete Contact where Id = " + retrievedContact.getId());
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Account Delete Op: Hibernate is unable to communicate with database");
			return "{\"output\":\"failure\"}";
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return "{\"output\":\"success\"}";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getAllContactsofAccount(int accountID) {
		try {
			Session session = getSession();
			Query query = session.createQuery("from Contact where accountID = " + accountID);
			List<Contact> contacts = (List<Contact>) query.list();
			return contacts;
		} catch (Exception aex) {
			throw new DaoException(aex);
		}
	}

	@Override
	public Contact searchContactByID(int contactID) {
		Session session = null;
		Contact retrievedContact = null;
		try {
			session = getSession();
			session.beginTransaction();
			retrievedContact = (Contact) session.get(Contact.class, contactID);
			if (retrievedContact == null) {
				if (session.isOpen()) {
					session.close();
				}
				System.out.println("Contact Search Op: Contact not Found!");
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Contact Search Op: Hibernate is unable to communicate with database");
			return null;
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return retrievedContact;
	}

	@Override
	public List<Address> getAllAddressesofContacts(int accountID) {
		List<Contact> retrievedContacts = null;
		List<Address> addresses = new ArrayList<>();
		retrievedContacts = getAllContactsofAccount(accountID);
		if (retrievedContacts == null) {
			System.out.println("Contact Search Op: Contacts not Found!");
			return null;
		}
		for(Contact contact : retrievedContacts) {
			addresses.add(contact.getAddress());
		}
		return addresses;
	}

}

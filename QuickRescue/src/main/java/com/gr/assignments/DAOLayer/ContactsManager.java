package com.gr.assignments.DAOLayer;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.gr.assignments.pojorepository.Account;
import com.gr.assignments.pojorepository.Contact;

public class ContactsManager implements IManager {
	private SessionsProvider sessionsProvider = new SessionsProvider();
	private Session session = null;

	public int addDetails(Object object) {
		int contactID;
		try {
			Contact contact = ((ContactObjMaker) object).getContact();
			int accountID = ((ContactObjMaker) object).getAccountID();
			session = sessionsProvider.getSession();
			session.beginTransaction();
			Account retrievedAccount = session.get(Account.class, accountID);
			if (retrievedAccount == null) {
				if (session.isOpen()) {
					session.close();
				}
				System.out.println("ADD ACTION: Associated Account does not exist");
				return -1;
			}
			retrievedAccount.getContactList().add(contact);
			contact.setAccount(retrievedAccount);
			contact.getAddress().setContact(contact);
			contactID = (Integer) session.save(contact);
			session.save(contact.getAddress());
			session.getTransaction().commit();
		} catch (HibernateException hbe) {
			System.out.println("Hibernate is unable to communicate with database");
			session.getTransaction().rollback();
			return -1;
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return contactID;
	}

	public Object updateDetails(int ID, Object object) {
		Contact retrievedContact = null;
		try {
			session = sessionsProvider.getSession();
			session.beginTransaction();
			retrievedContact = session.get(Contact.class, ID);
			if (retrievedContact == null) {
				if (session.isOpen()) {
					session.close();
				}
				System.out.println("UPDATE ACTION: Contact ID not Found!");
				return -1;
			}
			retrievedContact.setFirstName(((Contact) object).getFirstName());
			retrievedContact.setLastName(((Contact) object).getLastName());
			retrievedContact.setEmailID(((Contact) object).getEmailID());
			retrievedContact.setStatus(((Contact) object).getStatus());
			retrievedContact.setGender(((Contact) object).getGender());
			retrievedContact.setPhoneNo(((Contact) object).getPhoneNo());
			retrievedContact.getAddress().setCity(((Contact) object).getAddress().getCity());
			retrievedContact.getAddress().setStreet(((Contact) object).getAddress().getStreet());
			retrievedContact.getAddress().setState(((Contact) object).getAddress().getState());
			retrievedContact.getAddress().setCountry(((Contact) object).getAddress().getCountry());
			retrievedContact.getAddress().setContact(retrievedContact);
			retrievedContact.setAccount(retrievedContact.getAccount());
			session.update(retrievedContact);
			session.getTransaction().commit();
		} catch (HibernateException hbe) {
			session.getTransaction().rollback();
			hbe.printStackTrace();
			System.out.println("Hibernate is unable to communicate with database");
			return null;
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return retrievedContact;
	}

	public Object deleteDetails(int ID) {
		Contact retrievedContact = null;
		try {
			session = sessionsProvider.getSession();
			session.beginTransaction();
			retrievedContact = session.get(Contact.class, ID);
			if (retrievedContact == null) {
				if (session.isOpen()) {
					session.close();
				}
				System.out.println("DELETE ACTION: Contact ID not Found!");
				return -1;
			}
			session.delete(retrievedContact);
			session.getTransaction().commit();
		} catch (HibernateException hbe) {
			session.getTransaction().rollback();
			System.out.println("Hibernate is unable to communicate with database");
			return null;
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return retrievedContact;
	}

	@SuppressWarnings("unchecked")
	public List<Object> viewAllDetails() {
		List<Object> contacts = null;
		try {
			session = sessionsProvider.getSession();
			session.beginTransaction();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Contact> criteriaQuery = criteriaBuilder.createQuery(Contact.class);
			Root<Contact> root = criteriaQuery.from(Contact.class);
			criteriaQuery.select(root);
			Query query = session.createQuery(criteriaQuery);
			contacts = query.getResultList();
			session.getTransaction().commit();
		} catch (HibernateException hexp) {
			System.out.println("Hibernate is unable to communicate with database");
			session.getTransaction().rollback();
			return null;
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return contacts;
	}

}

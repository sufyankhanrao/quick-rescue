package com.gr.assignments.DAOLayer;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.gr.assignments.pojorepository.Account;

public class AccountsManager implements IManager {
	private SessionsProvider sessionsProvider = new SessionsProvider();
	private Session session = null;

	public int addDetails(Object object) {
		int insertedID;
		try {
			session = sessionsProvider.getSession();
			session.beginTransaction();
			insertedID = (Integer) session.save(object);
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
		return insertedID;
	}

	public Object updateDetails(int ID, Object object) {
		Account retrievedAccount = null;
		try {
			session = sessionsProvider.getSession();
			session.beginTransaction();
			retrievedAccount = session.get(Account.class, ID);
			retrievedAccount.setName(((Account) object).getName());
			retrievedAccount.setEmailDomain(((Account) object).getEmailDomain());
			retrievedAccount.setTimezoneCity(((Account) object).getTimezoneCity());
			session.update(retrievedAccount);
			session.getTransaction().commit();
		} catch (HibernateException hbe) {
			session.getTransaction().rollback();
			System.out.println("Hibernate is unable to communicate with database");
			return null;
		} catch (NullPointerException e) {
			System.out.println("UPDATE ACTION: Account ID not Found!");
			return null;
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return retrievedAccount;
	}

	public Object deleteDetails(int ID) {
		Account retrievedAccount = null;
		try {
			session = sessionsProvider.getSession();
			session.beginTransaction();
			retrievedAccount = session.get(Account.class, ID);
			if (retrievedAccount == null) {
				if (session.isOpen()) {
					session.close();
				}
				System.out.println("DELETE ACTION: Account ID not Found!");
				return -1;
			}
			session.delete(retrievedAccount);
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
		return retrievedAccount;
	}

	@SuppressWarnings("unchecked")
	public List<Object> viewAllDetails() {
		List<Object> accounts = null;
		try {
			session = sessionsProvider.getSession();
			session.beginTransaction();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
			Root<Account> root = criteriaQuery.from(Account.class);
			criteriaQuery.select(root);
			Query query = session.createQuery(criteriaQuery);
			accounts = query.getResultList();
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
		return accounts;
	}

}

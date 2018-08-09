package com.gr.qrapi.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.gr.common.dao.AbstractHibernateDao;
import com.gr.common.dao.DaoException;
import com.gr.common.dao.DaoManager;
import com.gr.qrapi.core.model.Account;
import com.gr.qrapi.core.model.AlertProfile;
import com.gr.qrapi.core.model.Contact;

public class AccountDaoHibernateImpl extends AbstractHibernateDao<Account, Integer> implements AccountDao {

	public static AccountDao getDao() {
		return DaoManager.getInstance().getDao(AccountDao.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getAllAccounts() {

		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(Account.class);
			criteria.setMaxResults(100);
			List<Account> accounts = (List<Account>) criteria.list();
			return accounts;
		} catch (Exception aex) {
			throw new DaoException(aex);
		}
	}

	@Override
	public String addAccount(Account account) {
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			session.save(account);
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

	@SuppressWarnings("unused")
	@Override
	public String updateAccount(int accountID, Account account) {
		Account retrievedAccount = null;
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"update Account set Name = :name, EmailDomain = :emailDomain, TimeZoneCity = :timeZoneCity, username = :username, Password = :password where Id = "
							+ accountID);
			query.setParameter("name", account.getName());
			query.setParameter("emailDomain", account.getEmailDomain());
			query.setParameter("timeZoneCity", account.getTimeZoneCity());
			query.setParameter("username", account.getUsername());
			query.setParameter("password", account.getPassword());
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
	public String deleteAccount(int accountID) {
		Account retrievedAccount = null;
		Session session = null;
		Query query;
		try {
			session = getSession();
			session.beginTransaction();
			retrievedAccount = (Account) session.get(Account.class, accountID);
			if (retrievedAccount == null) {
				if (session.isOpen()) {
					session.close();
				}
				System.out.println("Account Delete Op: Account ID not Found!");
				return "{\"output\":\"failure\"}";
			}
			List<Contact> contacts = getContactList(retrievedAccount.getId(), session);
			for (Contact contact : contacts) {
				query = session.createQuery("delete Address where Id = " + contact.getAddress().getId());
				query.executeUpdate();
				query = session.createQuery("delete Contact where Id = " + contact.getId());
				query.executeUpdate();
			}

			List<AlertProfile> profiles = getProfilesList(retrievedAccount.getId(), session);
			for (AlertProfile alertProfile : profiles) {
				query = session.createQuery("delete Location where Id = " + alertProfile.getLocation().getId());
				query.executeUpdate();
				query = session.createQuery("delete AlertProfile where Id = " + alertProfile.getId());
				query.executeUpdate();
			}

			query = session.createQuery("delete Account where Id = " + retrievedAccount.getId());
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
	private List<AlertProfile> getProfilesList(Integer accountID, Session session) throws Exception {
		List<AlertProfile> profiles;
		Query query = session.createQuery("from AlertProfile where accountID = " + accountID);
		profiles = (List<AlertProfile>) query.list();
		System.out.println(profiles.size());
		return profiles;
	}

	@SuppressWarnings({ "unchecked" })
	private List<Contact> getContactList(Integer accountID, Session session) {
		List<Contact> contacts;
		Query query = session.createQuery("from Contact where accountID = " + accountID);
		contacts = (List<Contact>) query.list();

		System.out.println(contacts.size());
		return contacts;
	}

	@Override
	public Account searchAccountByID(int accountID) {
		Session session = null;
		Account retrievedAccount = null;
		try {
			session = getSession();
			session.beginTransaction();
			retrievedAccount = (Account) session.get(Account.class, accountID);
			if (retrievedAccount == null) {
				if (session.isOpen()) {
					session.close();
				}
				System.out.println("Account Search Op: Account not Found!");
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Account Search Op: Hibernate is unable to communicate with database");
			return null;
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return retrievedAccount;
	}

	@Override
	public String getLogin(String username, String password) {
		Session session = null;
		Integer id = null;
		try {
			session = getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Account.class).add(
					Restrictions.and(Restrictions.eq("username", username), Restrictions.eq("password", password)));
			Projection projection = Projections.property("id");
			criteria.setProjection(projection);
			id = (Integer) criteria.uniqueResult();
			if (id == null) {
				if (session.isOpen()) {
					session.close();
				}

				return "{\"output\":\"failure\"}";
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Login Op: Hibernate is unable to communicate with database");
			e.printStackTrace();
			return "{\"output\":\"failure\"}";
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

		return "{\"output\":\"" + id + "\"}";
	}

}

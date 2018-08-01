package com.gr.qrapi.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.gr.common.dao.AbstractHibernateDao;
import com.gr.common.dao.DaoException;
import com.gr.common.dao.DaoManager;
import com.gr.qrapi.core.model.Account;

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
		String result = null;
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			result = (Integer) session.save(account) + "";
			session.getTransaction().commit();
		} catch (HibernateException hbe) {
			session.getTransaction().rollback();
			return "Account Add Op: Hibernate is unable to communicate with database";
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return result;
	}

	@Override
	public Account updateAccount(int accountID, Account account) {
		Account retrievedAccount = null;
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			retrievedAccount = (Account) session.get(Account.class, accountID);
			retrievedAccount.setName((account.getName()));
			retrievedAccount.setEmailDomain(account.getEmailDomain());
			retrievedAccount.setTimeZoneCity(account.getTimeZoneCity());
			session.update(retrievedAccount);
			session.getTransaction().commit();
		} catch (HibernateException hbe) {
			session.getTransaction().rollback();
			System.out.println("Account Update Op:Hibernate is unable to communicate with database");
			return null;
		} catch (NullPointerException e) {
			System.out.println("Account Update Op: Account ID not Found!");
			return null;
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return retrievedAccount;
	}

	@Override
	public Account deleteAccount(int accountID) {
		Account retrievedAccount = null;
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			retrievedAccount = (Account) session.get(Account.class, accountID);
			if (retrievedAccount == null) {
				if (session.isOpen()) {
					session.close();
				}
				System.out.println("Account Delete Op: Account ID not Found!");
				return null;
			}
			session.delete(retrievedAccount);
			System.out.println(retrievedAccount.getName());
			session.getTransaction().commit();
		} catch (HibernateException hbe) {
			session.getTransaction().rollback();
			System.out.println("Account Delete Op: Hibernate is unable to communicate with database");
			return null;
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return retrievedAccount;
	}
}

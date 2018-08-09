package com.gr.qrapi.core.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.gr.common.dao.AbstractHibernateDao;
import com.gr.common.dao.DaoException;
import com.gr.common.dao.DaoManager;
import com.gr.qrapi.core.model.Account;
import com.gr.qrapi.core.model.AlertProfile;
import com.gr.qrapi.core.model.Contact;;

public class AlertProfileDaoHibernateImpl extends AbstractHibernateDao<AlertProfile, Integer>
		implements AlertProfileDao {
	public static AlertProfileDao getDao() {
		return DaoManager.getInstance().getDao(AlertProfileDao.class);
	}

	@Override
	public String addProfile(int accountID, AlertProfile alertProfile) {
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
			retrievedAccount.getProfiles().add(alertProfile);
			session.saveOrUpdate(retrievedAccount);
			session.save(alertProfile);
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
	public String updateProfile(int profileID, AlertProfile alertProfile) {
		AlertProfile retrievedProfile = null;
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			retrievedProfile = (AlertProfile) session.get(AlertProfile.class, profileID);
			if (retrievedProfile == null) {
				if (session.isOpen()) {
					session.close();
				}
				return "{\"output\":\"failure\"}";
			}
			Query query = session.createQuery("update Location set City = :city, Country = :country where Id = "
					+ retrievedProfile.getLocation().getId());
			query.setParameter("city", alertProfile.getLocation().getCity());
			query.setParameter("country", alertProfile.getLocation().getCountry());
			query.executeUpdate();

			query = session.createQuery("update AlertProfile set Name = :name where Id = " + profileID);
			query.setParameter("name", alertProfile.getName());
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
	public String deleteProfile(int profileID) {
		AlertProfile retrievedProfile = null;
		Session session = null;
		Query query;
		try {
			session = getSession();
			session.beginTransaction();
			retrievedProfile = (AlertProfile) session.get(AlertProfile.class, profileID);
			if (retrievedProfile == null) {
				if (session.isOpen()) {
					session.close();
				}
				System.out.println("Contact Delete Op: Associated Account ID not Found!");
				return "{\"output\":\"failure\"}";
			}
			query = session.createQuery("delete Location where Id = " + retrievedProfile.getLocation().getId());
			query.executeUpdate();
			query = session.createQuery("delete AlertProfile where Id = " + retrievedProfile.getId());
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
	public List<AlertProfile> getAllProfiles() {
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(AlertProfile.class);
			criteria.setMaxResults(100);
			List<AlertProfile> alertProfiles = (List<AlertProfile>) criteria.list();
			return alertProfiles;
		} catch (Exception aex) {
			throw new DaoException(aex);
		}
	}

	@Override
	public AlertProfile searchProfileByID(int alertProfileID) {
		Session session = null;
		AlertProfile retrievedProfile = null;
		try {
			session = getSession();
			session.beginTransaction();
			retrievedProfile = (AlertProfile) session.get(AlertProfile.class, alertProfileID);
			if (retrievedProfile == null) {
				if (session.isOpen()) {
					session.close();
				}
				System.out.println("Profile Search Op: Profile not Found!");
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Profile Search Op: Hibernate is unable to communicate with database");
			return null;
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return retrievedProfile;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AlertProfile> getProfilesByAccountID(int accountID) {
		try {
			Session session = getSession();
			Query query = session.createQuery("from AlertProfile where accountID = " + accountID);
			List<AlertProfile> profile = (List<AlertProfile>) query.list();
			return profile;
		} catch (Exception aex) {
			throw new DaoException(aex);
		}
	}
}

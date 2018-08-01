package com.gr.qrapi.core.dao;

import java.util.List;

import com.gr.common.dao.AbstractHibernateDao;
import com.gr.common.dao.DaoManager;
import com.gr.qrapi.core.model.AlertProfile;;

public class AlertProfileDaoHibernateImpl extends AbstractHibernateDao<AlertProfile, Integer>
		implements AlertProfileDao {
	public static AlertProfileDao getDao() {
		return DaoManager.getInstance().getDao(AlertProfileDao.class);
	}

	@Override
	public String addProfile(int accountID, AlertProfile alertProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlertProfile updateProfile(int profileID, AlertProfile alertProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlertProfile deleteProfile(int profileID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AlertProfile> getAllProfiles() {
		// TODO Auto-generated method stub
		return null;
	}
}

package com.gr.qrapi.core.dao;

import java.util.List;

import com.gr.common.dao.GenericDao;
import com.gr.qrapi.core.model.AlertProfile;

public interface AlertProfileDao extends GenericDao<AlertProfile, Integer> {
	String addProfile(int accountID, AlertProfile alertProfile);

	AlertProfile updateProfile(int profileID, AlertProfile alertProfile);

	AlertProfile deleteProfile(int profileID);

	List<AlertProfile> getAllProfiles();
}

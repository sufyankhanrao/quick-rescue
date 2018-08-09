package com.gr.qrapi.core.dao;

import java.util.List;

import com.gr.common.dao.GenericDao;
import com.gr.qrapi.core.model.AlertProfile;

public interface AlertProfileDao extends GenericDao<AlertProfile, Integer> {
	String addProfile(int accountID, AlertProfile alertProfile);

	String updateProfile(int profileID, AlertProfile alertProfile);

	String deleteProfile(int profileID);

	List<AlertProfile> getAllProfiles();

	AlertProfile searchProfileByID(int alertProfileID);
	
	List<AlertProfile> getProfilesByAccountID(int accountID);
}

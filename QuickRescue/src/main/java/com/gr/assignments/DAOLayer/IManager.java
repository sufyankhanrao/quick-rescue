package com.gr.assignments.DAOLayer;

import java.util.List;

public interface IManager {
	// Add new Account in the database
	public int addDetails(Object object);

	// Update an existing Account in the database with new Account
	public Object updateDetails(int ID, Object object);

	// Delete an existing Account from the database
	public Object deleteDetails(int ID);

	// View all existing Accounts in the database
	public List<Object> viewAllDetails();
}

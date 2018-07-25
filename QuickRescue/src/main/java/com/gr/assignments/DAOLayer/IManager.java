package com.gr.assignments.DAOLayer;

import java.util.List;

public interface IManager {
	// Add Details in the database
	public int addDetails(Object object);

	// Update Details in the database
	public Object updateDetails(int ID, Object object);

	// Delete Details from the database
	public Object deleteDetails(int ID);

	// View all existing Details in the database
	public List<Object> viewAllDetails();
}

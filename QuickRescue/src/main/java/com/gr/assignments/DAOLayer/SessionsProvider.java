package com.gr.assignments.DAOLayer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.gr.assignments.factoryprovider.SessionFactoryProvider;

public class SessionsProvider {
	private SessionFactory sessionFactory = null;

	public SessionsProvider() {
		openFactory();
	}

	private void openFactory() {
		sessionFactory = (SessionFactory) new SessionFactoryProvider().getFactory();
	}

	public Session getSession() {
		return sessionFactory.openSession();
	}

	public void closeFactory() {
		if (this.sessionFactory.isOpen())
			this.sessionFactory.close();
	}
}

package com.gr.assignments.factoryprovider;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactories {
	private static SessionFactories sessionFactories = new SessionFactories();
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	private SessionFactories() {

	}

	public static SessionFactories getInstance() {
		return sessionFactories;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}

package com.gr.assignments.factoryprovider;

public class SessionFactoryProvider {
	private SessionFactories sessionFactories = SessionFactories.getInstance();

	public SessionFactoryProvider() {
	}

	public Object getFactory() {
		return sessionFactories.getSessionFactory();
	}
}

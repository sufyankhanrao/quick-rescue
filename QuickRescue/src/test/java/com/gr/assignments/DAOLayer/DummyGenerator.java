package com.gr.assignments.DAOLayer;

import com.gr.assignments.pojorepository.Account;
import com.gr.assignments.pojorepository.Address;
import com.gr.assignments.pojorepository.Contact;

public class DummyGenerator {

	public DummyGenerator() {
	}

	// Utility method for generating a dummy Account for testing
	public Account generateDummyAccount() {
		return new Account("Sufyan", "com", "Islamabad");
	}

	// Utility method for generating a dummy Contact for testing
	public Contact generateDummyContact() {
		return new Contact("Sheraz", "ahmed", "sherazahmed18@gmail.com", "male", "+923427676888", "active",
				new Address("street 12", "rwp", "punjab", "pakistan"));
	}
}

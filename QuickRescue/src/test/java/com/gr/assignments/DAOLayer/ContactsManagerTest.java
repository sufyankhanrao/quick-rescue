package com.gr.assignments.DAOLayer;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.gr.assignments.DAOLayer.AccountsManager;
import com.gr.assignments.DAOLayer.ContactObjMaker;
import com.gr.assignments.DAOLayer.ContactsManager;
import com.gr.assignments.DAOLayer.IManager;
import com.gr.assignments.pojorepository.Account;
import com.gr.assignments.pojorepository.Address;
import com.gr.assignments.pojorepository.Contact;

public class ContactsManagerTest {
	IManager accountManager = new AccountsManager();
	IManager contactManager = new ContactsManager();
	DummyGenerator dummy = new DummyGenerator();

	// Testing Add Contact unit
	@Test
	public void testAddContact() {
		Account account = dummy.generateDummyAccount();
		int accountID = accountManager.addDetails(account);
		ContactObjMaker contactObject = new ContactObjMaker(dummy.generateDummyContact(), accountID);
		contactManager.addDetails(contactObject);
		int actualResult = accountManager.viewAllDetails().size();
		assertEquals(1, actualResult);
		accountManager.deleteDetails(accountID);
	}

	// Testing delete Contact unit
	@Test
	public void testDeleteContact() {
		int accountID = accountManager.addDetails(dummy.generateDummyAccount());
		ContactObjMaker contactObject = new ContactObjMaker(dummy.generateDummyContact(), accountID);
		int contactID = contactManager.addDetails(contactObject);
		int afterAccountAddition = accountManager.viewAllDetails().size();
		int afterContactAddition = contactManager.viewAllDetails().size();
		contactManager.deleteDetails(contactID);
		int afterAccountDeletion = accountManager.viewAllDetails().size();
		int afterContactDeletion = contactManager.viewAllDetails().size();
		assertEquals(1, afterAccountAddition);
		assertEquals(1, afterContactAddition);
		assertEquals(0, afterContactDeletion);
		assertEquals(1, afterAccountDeletion);
		accountManager.deleteDetails(accountID);
	}

	// Testing update Contact unit
	@Test
	public void testUpdateContact() {
		Contact expactedContact = new Contact("Awab", "Khan", "awab14GR@gmail.com", "male", "+923427676999", "inactive",
				new Address("street 100", "lhr", "punjab", "pakistan"));
		int accountID = accountManager.addDetails(dummy.generateDummyAccount());
		ContactObjMaker contactObject = new ContactObjMaker(dummy.generateDummyContact(), accountID);
		int contactID = contactManager.addDetails(contactObject);
		Contact actualContact = (Contact) contactManager.updateDetails(contactID, expactedContact);
		assertEquals(contactID, actualContact.getId());
		assertEquals(expactedContact.getFirstName(), actualContact.getFirstName());
		assertEquals(expactedContact.getLastName(), actualContact.getLastName());
		assertEquals(expactedContact.getEmailID(), actualContact.getEmailID());
		assertEquals(expactedContact.getStatus(), actualContact.getStatus());
		assertEquals(expactedContact.getGender(), actualContact.getGender());
		assertEquals(expactedContact.getPhoneNo(), actualContact.getPhoneNo());
		assertEquals(expactedContact.getAddress().getStreet(), actualContact.getAddress().getStreet());
		assertEquals(expactedContact.getAddress().getCity(), actualContact.getAddress().getCity());
		assertEquals(expactedContact.getAddress().getState(), actualContact.getAddress().getState());
		assertEquals(expactedContact.getAddress().getCountry(), actualContact.getAddress().getCountry());
		accountManager.deleteDetails(accountID);
	}

	//Testing view all Contacts unit
	@Test
	public void testViewAllContacts() {
		Contact contact = dummy.generateDummyContact();
		int accountID = accountManager.addDetails(dummy.generateDummyAccount());
		ContactObjMaker contactObject = new ContactObjMaker(dummy.generateDummyContact(), accountID);
		int contactID=contactManager.addDetails(contactObject);
		List<Object> retrievedObjects = contactManager.viewAllDetails();
		for (Object retrievedObject : retrievedObjects) {
			Contact retrievedContact = ((Contact) retrievedObject);
			assertEquals(contactID, retrievedContact.getId());
			assertEquals(contact.getFirstName(), retrievedContact.getFirstName());
			assertEquals(contact.getLastName(), retrievedContact.getLastName());
			assertEquals(contact.getEmailID(), retrievedContact.getEmailID());
			assertEquals(contact.getStatus(), retrievedContact.getStatus());
			assertEquals(contact.getGender(), retrievedContact.getGender());
			assertEquals(contact.getPhoneNo(), retrievedContact.getPhoneNo());
			assertEquals(contact.getAddress().getStreet(), retrievedContact.getAddress().getStreet());
			assertEquals(contact.getAddress().getCity(), retrievedContact.getAddress().getCity());
			assertEquals(contact.getAddress().getState(), retrievedContact.getAddress().getState());
			assertEquals(contact.getAddress().getCountry(), retrievedContact.getAddress().getCountry());
		}
		accountManager.deleteDetails(accountID);
	}
}

package com.gr.assignments.DAOLayer;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.gr.assignments.DAOLayer.AccountsManager;
import com.gr.assignments.DAOLayer.ContactObjMaker;
import com.gr.assignments.DAOLayer.ContactsManager;
import com.gr.assignments.DAOLayer.IManager;
import com.gr.assignments.pojorepository.Account;

public class AccountsManagerTest {
	IManager accountManager = new AccountsManager();
	IManager contactManager = new ContactsManager();
	DummyGenerator dummy = new DummyGenerator();

	// Testing Add Account unit
	@Test
	public void testAddAccount() {
		Account account = dummy.generateDummyAccount();
		accountManager.addDetails(account);
		int actualResult = accountManager.viewAllDetails().size();
		assertEquals(1, actualResult);
		accountManager.deleteDetails(account.getId());
	}

	// Testing update Account unit
	@Test
	public void testUpdateAccount() {
		int ID = accountManager.addDetails(dummy.generateDummyAccount());
		Account newAccount = new Account("Asad", "org", "Lahore");
		Account retrievedAccount = (Account) accountManager.updateDetails(ID, newAccount);
		assertEquals(newAccount.getName(), retrievedAccount.getName());
		assertEquals(newAccount.getEmailDomain(), retrievedAccount.getEmailDomain());
		assertEquals(newAccount.getTimezoneCity(), retrievedAccount.getTimezoneCity());
		accountManager.deleteDetails(retrievedAccount.getId());
	}

	// Testing delete Account unit
	@Test
	public void testDeleteAccount() {
		int ID = accountManager.addDetails(dummy.generateDummyAccount());
		ContactObjMaker contactObject = new ContactObjMaker(dummy.generateDummyContact(), ID);
		contactManager.addDetails(contactObject);
		int afterAccountAddition = accountManager.viewAllDetails().size();
		int afterContactAddition = contactManager.viewAllDetails().size();
		accountManager.deleteDetails(ID);
		int afterAccountDeletion = accountManager.viewAllDetails().size();
		int afterContactDeletion = contactManager.viewAllDetails().size();
		assertEquals(1, afterAccountAddition);
		assertEquals(1, afterContactAddition);
		assertEquals(0, afterContactDeletion);
		assertEquals(0, afterAccountDeletion);
	}

	// Testing view all Accounts unit
	@Test
	public void testViewAllAccounts() {
		Account account = dummy.generateDummyAccount();
		int ID = accountManager.addDetails(account);
		List<Object> retrievedObjects = accountManager.viewAllDetails();
		for (Object retrievedObject : retrievedObjects) {
			Account retrievedAccount = (Account) retrievedObject;
			assertEquals(account.getId(), retrievedAccount.getId());
			assertEquals(account.getName(), retrievedAccount.getName());
			assertEquals(account.getEmailDomain(), retrievedAccount.getEmailDomain());
			assertEquals(account.getTimezoneCity(), retrievedAccount.getTimezoneCity());
		}
		accountManager.deleteDetails(ID);
	}

}

package com.gr.qrapi.core.dao;

import java.util.List;

import com.gr.common.dao.GenericDao;
import com.gr.qrapi.core.model.Account;

public interface AccountDao extends GenericDao<Account, Integer> {

	String addAccount(Account account);

	Account updateAccount(int accountID, Account account);

	Account deleteAccount(int accountID);

	List<Account> getAllAccounts();

}

package com.cg.paymentwallet.dao;

import com.cg.paymentwallet.dto.Customer;
import com.cg.paymentwallet.dto.Wallet;
import com.cg.paymentwallet.exception.WalletException;

public interface IWalletDao {
	public void createAccount(Customer customer);

	public double showBalance(String userId);

	public void deposit(String userId, double amount);

	public void withdraw(String userId, double amount);

	public void fundTransfer(String userIdSender, String userIdReceiver, double amount) throws WalletException;

	public String printTransactions(String userId);

	public Customer login(String id, String password) throws WalletException;

	void beginTransaction();

	void commitTransaction();
}

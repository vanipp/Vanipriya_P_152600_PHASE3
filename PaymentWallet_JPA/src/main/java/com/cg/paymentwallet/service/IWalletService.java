package com.cg.paymentwallet.service;

import com.cg.paymentwallet.dto.Customer;
import com.cg.paymentwallet.exception.WalletException;

public interface IWalletService {

	public void createAccount(Customer customer);

	public double showBalance(String phNumber);

	public boolean deposit(String phNumber, double amount);

	public boolean withdraw(String phNumber, double amount);

	public boolean fundTransfer(String phSender, String phReceiver, double amount) throws WalletException;

	public boolean validateDetails(Customer customer) throws WalletException;

	public Customer login(String id, String password) throws WalletException;

	public String printTransaction(String userId);
}
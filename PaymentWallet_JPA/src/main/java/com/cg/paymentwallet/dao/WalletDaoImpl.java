package com.cg.paymentwallet.dao;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import com.cg.paymentwallet.dto.Customer;
import com.cg.paymentwallet.dto.Wallet;
import com.cg.paymentwallet.exception.WalletException;

public class WalletDaoImpl implements IWalletDao {

	private EntityManager manager = null;

	public WalletDaoImpl() {
		manager = JPAUtil.getEntityManager();
	}

	public void createAccount(Customer customer) {

		manager.persist(customer);

	}

	public double showBalance(String userId) {
		double balance = manager.find(Customer.class, userId).getWallet().getBalance();
		return balance;
	}

	public void deposit(String userId, double amount) {

		Customer customer = manager.find(Customer.class, userId);
		Wallet wallet = customer.getWallet();
		wallet.setBalance(customer.getWallet().getBalance() + amount);

		LocalDateTime time = LocalDateTime.now();
		String trans = customer.getWallet().getTransaction().concat(amount + " Deposited at " + time + "aaa");
		wallet.setTransaction(trans);
		customer.setWallet(wallet);
		manager.merge(customer);
	}

	public void withdraw(String userId, double amount) {

		Customer customer = manager.find(Customer.class, userId);
		Wallet wallet = customer.getWallet();
		wallet.setBalance(customer.getWallet().getBalance() - amount);

		LocalDateTime time = LocalDateTime.now();
		String trans = customer.getWallet().getTransaction().concat(amount + " Withdrawn at " + time + "aaa");
		wallet.setTransaction(trans);
		customer.setWallet(wallet);
		manager.merge(customer);

	}

	public void fundTransfer(String userIdSender, String userIdReceiver, double amount) throws WalletException {

		Customer customer = manager.find(Customer.class, userIdReceiver);
		Wallet wallet = customer.getWallet();
		wallet.setBalance(customer.getWallet().getBalance() + amount);
		LocalDateTime time = LocalDateTime.now();
		String trans = customer.getWallet().getTransaction()
				.concat(amount + " Received from " + userIdSender + " at " + time + " aaa");
		wallet.setTransaction(trans);
		customer.setWallet(wallet);
		manager.merge(customer);

		Customer customer2 = manager.find(Customer.class, userIdSender);
		Wallet wallet2 = customer2.getWallet();
		wallet2.setBalance(customer2.getWallet().getBalance() - amount);

		String trans2 = customer2.getWallet().getTransaction()
				.concat(amount + " Sent to " + userIdReceiver + " at " + time + " aaa");
		wallet2.setTransaction(trans2);
		customer2.setWallet(wallet2);
		manager.merge(customer2);

	}

	public String printTransactions(String userId) {

		Customer customer = manager.find(Customer.class, userId);
		String trans = customer.getWallet().getTransaction();
		return trans;
	}

	public Customer login(String id, String password) throws WalletException {

		String pass = null;

		Customer customer = manager.find(Customer.class, id);
		pass = customer.getWallet().getPassword();
		if (pass.equals(password)) {
			return customer;
		}
		return null;
	}

	public void beginTransaction() {
		manager.getTransaction().begin();
	}

	public void commitTransaction() {
		manager.getTransaction().commit();
	}

}
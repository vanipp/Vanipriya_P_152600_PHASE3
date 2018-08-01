package com.cg.paymentwallet.dto;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Wallet implements Serializable {

	private static final long serialVersionUID = 1L;

	private double balance;
	private String password;
	private String transaction;

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Wallet() {

	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
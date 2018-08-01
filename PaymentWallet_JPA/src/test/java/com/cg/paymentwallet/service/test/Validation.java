package com.cg.paymentwallet.service.test;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cg.paymentwallet.dao.IWalletDao;
import com.cg.paymentwallet.dao.WalletDaoImpl;
import com.cg.paymentwallet.dto.Customer;
import com.cg.paymentwallet.dto.Wallet;
import com.cg.paymentwallet.exception.WalletException;
import com.cg.paymentwallet.service.IWalletService;
import com.cg.paymentwallet.service.WalletServiceImpl;

public class Validation {
	IWalletService service = new WalletServiceImpl();
	IWalletDao dao = new WalletDaoImpl();

	@Test
	public void CheckForZeroDeposittest() throws WalletException {
		boolean condition = false;
		Customer customer = new Customer();
		customer.setName("Vanipriya");
		customer.setEmailId("vani@gmail.com");
		customer.setUserId("vanipp");
		customer.setPhNumber("9790963670");
		Wallet wallet = new Wallet();
		wallet.setPassword("vanigayu");
		customer.setWallet(wallet);
		dao.createAccount(customer);
		condition = service.deposit("vanipp", 0.0);
		assertFalse(condition);
	}

	@Test(expected = WalletException.class)
	public void CheckForInvalidNameTest() throws WalletException {
		Customer customer = new Customer();
		customer.setName("Gayathri");
		customer.setPhNumber("9790963670");
		customer.setEmailId("gayu@gmail.com");
		customer.setUserId("vani");
		Wallet wallet = new Wallet();
		wallet.setPassword("vani");
		customer.setWallet(wallet);
		service.validateDetails(customer);
	}

	@Test
	public void CheckForValidNameTest() throws WalletException {
		Customer customer = new Customer();
		customer.setName("Vanipriya");
		customer.setPhNumber("9790963670");
		customer.setEmailId("vani@gmail.com");
		customer.setUserId("vanipp");
		Wallet wallet = new Wallet();
		wallet.setPassword("vanigayu");
		wallet.setBalance(1000);
		customer.setWallet(wallet);
		boolean condition = service.validateDetails(customer);
		assertTrue(condition);
	}

	@Test(expected = WalletException.class)
	public void CheckForInvalidPhoneNumberTest() throws WalletException {
		Customer customer = new Customer();
		customer.setName("Vanipriya");
		customer.setPhNumber("979096");
		customer.setEmailId("abcd@gmail.com");
		customer.setUserId("vani");
		Wallet wallet = new Wallet();
		wallet.setPassword("vani");
		customer.setWallet(wallet);
		boolean condition = service.validateDetails(customer);
		assertFalse(condition);
	}


	@Test(expected = WalletException.class)
	public void CheckForInvalidEmailTest() throws WalletException {
		Customer customer = new Customer();
		customer.setName("Vanipriya");
		customer.setPhNumber("9790963670");
		customer.setEmailId("abcd");
		customer.setUserId("vani");
		Wallet wallet = new Wallet();
		wallet.setPassword("vani");
		customer.setWallet(wallet);
		boolean condition = service.validateDetails(customer);
		assertFalse(condition);
	}



	@Test(expected = WalletException.class)
	public void CheckForInvalidUserId() throws WalletException {
		Customer customer = new Customer();
		customer.setName("Vanipriya");
		customer.setPhNumber("9789789789");
		customer.setEmailId("vani@gmail.com");
		customer.setUserId("abc");
		Wallet wallet = new Wallet();
		wallet.setPassword("vanigayu");
		customer.setWallet(wallet);
		boolean condition = service.validateDetails(customer);
		assertFalse(condition);

	}



	@Test(expected = WalletException.class)
	public void CheckForInvalidassword() throws WalletException {
		Customer customer = new Customer();
		customer.setName("Vanipriya");
		customer.setPhNumber("9790963670");
		customer.setEmailId("vani@gmail.com");
		customer.setUserId("vani");
		Wallet wallet = new Wallet();
		wallet.setPassword("vani");
		customer.setWallet(wallet);
		boolean condition = service.validateDetails(customer);
		assertFalse(condition);

	}

	@Test
	public void CheckForValidPassword() throws WalletException {
		Customer customer = new Customer();
		customer.setName("Vanipriya");
		customer.setPhNumber("9790963670");
		customer.setEmailId("vani@gmail.com");
		customer.setUserId("vani");
		Wallet wallet = new Wallet();
		wallet.setPassword("vanigayu");
		customer.setWallet(wallet);
		boolean condition = service.validateDetails(customer);
		assertTrue(condition);

	}
}
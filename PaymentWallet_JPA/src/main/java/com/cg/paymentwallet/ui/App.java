package com.cg.paymentwallet.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.cg.paymentwallet.dto.Customer;
import com.cg.paymentwallet.dto.Wallet;
import com.cg.paymentwallet.exception.WalletException;
import com.cg.paymentwallet.service.IWalletService;
import com.cg.paymentwallet.service.WalletServiceImpl;

public class App {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		int choice1 = 0;

		do {
			System.out.println(" ");
			System.out.println("PAYMENT WALLET");
			System.out.println("");
			System.out.println("1 To Create Account");
			System.out.println("2 To Login");
			System.out.println("3 To Exit ");
			choice1 = scanner.nextInt();
			IWalletService service = new WalletServiceImpl();
			switch (choice1) {
			case 1:
				System.out.println("Enter your Phone Number");
				String phNum = scanner.next();
				System.out.println("Enter your Name");
				String name = scanner.next();
				System.out.println("Enter your Email ID");
				String emailId = scanner.next();
				System.out.println("Enter the EntryId to be created");
				String userId = scanner.next();
				System.out.println("Set your Password");
				String pass = scanner.next();
				System.out.println("Enter the Initial Amount to be Deposited");
				double balance = scanner.nextDouble();

				Customer customer= new Customer();
				customer.setPhNumber(phNum);
				customer.setName(name);
				customer.setEmailId(emailId);
				customer.setUserId(userId);
				Wallet wallet=new Wallet();
				wallet.setPassword(pass);
				wallet.setBalance(balance);
				wallet.setTransaction("aaa");
				customer.setWallet(wallet);

				boolean result = false;
				try {
					result = service.validateDetails(customer);
					service.createAccount(customer);
					System.out.println("Hello " + customer.getName());
					System.out.println("Your Account is Created Succesfully");
				} catch (WalletException e) {

					e.getMessage();
				}
				if (!result) {
					System.out.println("Cannot create account. Try Again");
				}

				break;

			case 2:
				System.out.println("Enter your EntryID");
				String id = scanner.next();
				System.out.println("Enter your Password");
				String password = scanner.next();
				Customer loginWallet = new Customer();
				try {
					loginWallet = service.login(id, password);
					login(loginWallet);
				} catch (WalletException e) {
					e.getMessage();
				}

				break;

			case 3:
				System.out.println("");
				System.out.println("Thank You");
				System.out.println("");
				break;

			default:
				System.out.println("Wrong choice! Try Again");
				break;
			}
		} while (choice1 != 3);

	}

	private static void login(Customer customer) {
		int choice2 = 0;
		System.out.println("Welcome " + customer.getName());
		do {
			System.out.println(" ");
			System.out.println("PAYMENT WALLET");
			System.out.println("");
			System.out.println("1 To Show Balance");
			System.out.println("2 To Withdraw Money from wallet");
			System.out.println("3 To Deosit Money to wallet");
			System.out.println("4 To Transfer Fund");
			System.out.println("5 To Print Transaction history");
			System.out.println("6 To LogOut");
			System.out.println("");
			choice2 = scanner.nextInt();
			IWalletService service = new WalletServiceImpl();
			String userId = customer.getUserId();

			switch (choice2) {
			case 1:
				double balance = service.showBalance(userId);
				System.out.println("Your Account Balance is " + balance);
				break;
			case 2:
				System.out.println("Enter the Amount to be Withdrawn");
				double withdrawAmount = scanner.nextDouble();
				if (service.withdraw(userId, withdrawAmount)) {
					System.out.println("Rupees " + withdrawAmount + " Withdrawn from your Wallet");
					System.out.println("Your Updated Account Balance in Rupees " + service.showBalance(userId));
				} else
					System.out.println("Withdrawal failed! Insuffecient Balance");

				break;
			case 3:
				System.out.println("Enter the Amount to be Deposited");
				double depositAmount = scanner.nextDouble();
				if (service.deposit(userId, depositAmount)) {
					System.out.println(" Deposited to your Wallet is " + depositAmount );
					System.out.println("Your Updated Balance is  " + service.showBalance(userId));
				} else
					System.out.println("Sorry, Unable to deposit");
				break;
			case 4:
				System.out.println("Enter the EntryId of Whom the fund is to be Transfered");
				String receiverUserId = scanner.next();
				System.out.println("Enter the amount to be Transfered");
				double transferAmount = scanner.nextDouble();
				try {
					if (service.fundTransfer(userId, receiverUserId, transferAmount)) {
						System.out.println("Rupees " + transferAmount + " Succesfully Transfered to " + receiverUserId);
						System.out.println("Your Updated Balance is" + service.showBalance(userId));
					}
				} catch (WalletException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:

				String list = customer.getWallet().getTransaction();
				System.out.println("TRANSACTION DETAILS");
				System.out.println("");
				String trans = service.printTransaction(userId);
				Scanner scanner=new Scanner(trans).useDelimiter("aaa");
				while (scanner.hasNext()) {
					String string=scanner.next();
					System.out.println(string);
				}
				break;
			case 6:
				System.out.println("");
				System.out.println("LOGGED OUT");
				break;

			default:
				System.out.println("Invalid Entry.....! try Again");
				break;
			}
		} while (choice2 != 6);

	}
}


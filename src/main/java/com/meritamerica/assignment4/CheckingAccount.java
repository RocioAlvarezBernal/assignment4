/*Create appropriate constructors
static CheckingAccount readFromString(String accountData) throws ParseException
Should throw a java.lang.NumberFormatException if String cannot be correctly parsed*/

package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckingAccount extends BankAccount {
//
//	static long accountNumber;
//	double balance;
//	static Date date;
	static double interest = 0.0001;
//	static int x = 0;
//	static String[] caArray = new String[x];

	CheckingAccount(double openingBalance, double interest2) {
		super(openingBalance, interest);
	}
	
	public CheckingAccount (long accountNumber, double openBalance, double interestRate, Date accountOpenedOn) {
		super(accountNumber, openBalance, interestRate, accountOpenedOn);
	}
	public boolean withdraw(double amount) {
		if(amount <= super.getBalance() && amount > 0) {
			this.balance = balance - amount;
			return true;
		}
		return false;
	}
//	CheckingAccount(long accountNumber, double balance, Date date, double interest) {
//		super(accountNumber, date, balance, interest);
//	}
	public boolean deposit(double amount) {
		if (amount > 0) {
			this.balance = balance + amount;
			return true;
		}
		return false;		
	}
	
	public String toString() {
		return "test String " + getBalance() + " " + getInterestRate()+ " " + /*futureValue(years)*/ futureValue(3);
	}

	public CheckingAccount(Long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
		super(accountNumber, balance, interestRate, accountOpenedOn);
	}

	public static CheckingAccount readFromString(String accountData) throws ParseException{
//		int testI = 0;
		String[] testName = accountData.split(",");
		SimpleDateFormat daate = new SimpleDateFormat("dd/MM/yyyy");
		
		
		long accountNumber = Long.parseLong(testName[0]);
		double balance = Double.parseDouble(testName[1]);
		double interestRate = Double.parseDouble(testName[2]);
		Date accountOpenedOn = daate.parse(testName[3]);
		CheckingAccount newCheckingAccount = new CheckingAccount(accountNumber, balance, interestRate, accountOpenedOn);
		return newCheckingAccount;
	}
}

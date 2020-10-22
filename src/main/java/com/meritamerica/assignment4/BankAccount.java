
package com.meritamerica.assignment4;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.NumberFormatException;

public class BankAccount {

	 Date accountOpenedOn;
	double balance;
	double interestRate;

	public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	


//========================BANK ACCOUNT CONSTRUCTORS===================================
	public BankAccount(double balance, double interestRate) {
		this.balance = balance;
	    this.interestRate = interestRate;
	    this.accountOpenedOn = new Date();
	    this.accountNumber = MeritBank.getNextAccountNumber();
	}


	
	BankAccount(double balance, double interestRate, Date accountOpenedOn){
		this(balance, interestRate);
		this.accountOpenedOn = accountOpenedOn;
	}
	    
	public BankAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn){
		this(balance, interestRate, accountOpenedOn);
		this.accountNumber = accountNumber;
	}
	   

	public java.util.Date getOpenedOn() {
//		Date accountOpenedOn = java.util.Date;
		return accountOpenedOn;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public boolean withdraw(double amount) {
		if(amount <= balance && amount > 0) {
			this.balance = balance - amount;
			return true;
		}
		return false;
	}
	public boolean deposit(double amount) {
		if (amount <= 0) {
	        return false;
	    } else {
	    	this.balance = balance + amount;
	    	return true;
	    }}

	public double futureValue(int years) {
		double futureVal = this.balance * Math.pow(1 + getInterestRate(), years);
		return futureVal;
	}

	static BankAccount readFromString(String accountData) throws ParseException, NumberFormatException {
		// setting the array index so it will always contain the same information in
		// that particular index ex: 0 is for date 1 is for balance etc might need a loop for this 
//		int testI = 0;
		try {
			// try this block of code this will test for errors while being executed
			String[] testName = accountData.split(",");
			// ^ will create a sting array with the parameter "accountData"
			SimpleDateFormat daate = new SimpleDateFormat("dd/MM/yyyy");
			// ^ parse the date using SimpleDateFormat

			// this block will set the details to an index in the string array according to the order they 
			//are in the provided file
			Long accountNumber = Long.parseLong(testName[0]);
	        double balance = Double.parseDouble(testName[1]);
	        double interestRate = Double.parseDouble(testName[2]);
	        Date accountOpenedOn = daate.parse(testName[3]);
	        return new BankAccount(accountNumber, balance, interestRate, accountOpenedOn);

		} catch (ParseException e) {
			e.printStackTrace();

			return null;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	// will create a string out of all the details provided and append the 
	String writeToString() {
		StringBuilder accountData = new StringBuilder();
		accountData.append("account opened on ").append(accountOpenedOn);
		accountData.append("balance ").append(balance);
		accountData.append("interest rate ").append(interestRate);
		accountData.append("account number ").append(accountNumber);
		return accountData.toString();
	}
}
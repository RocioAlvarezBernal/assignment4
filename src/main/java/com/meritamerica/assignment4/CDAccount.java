/*Create appropriate constructors
Override the deposit and withdraw methods to return false (CD Accounts cannot deposit new funds or withdraw funds until the term is reached)
static CDAccount readFromString(String accountData) throws ParseException
Should throw a java.lang.NumberFormatException if String cannot be correctly parsed
Override writeToString method to include term
*/

package com.meritamerica.assignment3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CDAccount extends BankAccount {

	Date date;
//	static Date accountOpenedOn;
//	double balance;
//	double interestRate;
//	double futureValue;
//	double accountTotal;
//	long accountNumber;
	private int term;
	CDOffering offering;

//	CDAccount(double openBalance, double interestRate, CDOffering offering) {
//		super(openBalance, offering.getInterestRate());
//		this.offering = offering;
////		this.balance = balance;
//	}

	public CDAccount( long accountNumber,Date accountOpenedOn, double balance, double interestRate, int term){
		super(balance, interestRate, accountOpenedOn);
		this.balance = balance;
		this.term = term; 

	}
	public CDAccount(CDOffering offering, double openingBalance){
//	     super();
		super(openingBalance,offering.getInterestRate());
		this.offering = offering;
		this.term = offering.getTerm();
	}
	 

//	public CDAccount(double balance, double interestRate, double interestRate, Date accountOpenedOn, int term) {
//		super(interestRate, interestRate, balance);
////		this.balance = balance;
//		this.term = term;
//	}
	public CDAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn, int term) {
		super(accountNumber, balance,interestRate,accountOpenedOn);
		this.term = term;
	}

	public int getTerm() {
		return this.term;
	}
	
	public Date getStartDate(){
		return date;
	}
	
	public double futureValue() {
		return super.futureValue(term);
	}
	
	@Override
	public boolean withdraw(double amount) {
        return false;
    }
    
    public boolean deposit(double amount) {
    	return false;
    }
	
	
//	public double getBalance() {
////		balance= this.balance;
//		return this.balance;
//
//	}

//	public double getInterestRate() {
//		return this.offering.getInterestRate();
//	}

//	public int getTerm() {
//		return this.term;
//	}
//
//	public Date getStartDate() {
//		return date;
////		return this.date;
//	}
////
////	public long getAccountNumber() {
////		return getAccountNumber();
////	}
//	
//	public boolean withdraw(double amount) {
//        return false;
//    }
//
//	public double futureValue() {
//		return super.futureValue(term);
//	}
//	 public boolean deposit(double amount) {
//	    	return false;
//	    }
//same as Bank account with different of term included therefore on stringOverRide we append the term as well 
	public static CDAccount readFromString(String accountData) throws ParseException, NumberFormatException {
//		int testI = 0;
		String[] testName = accountData.split(",");
		SimpleDateFormat daate = new SimpleDateFormat("dd/MM/yyyy");
		long accountNumber = Long.parseLong(testName[0]);
    	double balance = Double.parseDouble(testName[1]);
    	double interestRate = Double.parseDouble(testName[2]);
    	Date accountOpenedOn = daate.parse(testName[3]);
    	int term = Integer.parseInt(testName[4]);
//    	CDAccount newCDAccount = new CDAccount(interestRate,accountOpenedOn,term);
    	CDAccount newCDAccount= new CDAccount(accountNumber,balance,interestRate,accountOpenedOn,term /* offering */);
    	return newCDAccount;
    }
	
	public String writeToString() {
		StringBuilder stringOverRide = new StringBuilder();
		stringOverRide.append( writeToString()).append( ",");
		stringOverRide.append(term);
		return stringOverRide.toString();
	}
}
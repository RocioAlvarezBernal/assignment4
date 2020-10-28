//CheckingAccount addCheckingAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException
//If combined balance limit is exceeded, throw ExceedsCombinedBalanceLimitException
//Should also add a deposit transaction with the opening balance
//CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) throws ExceedsCombinedBalanceLimitException
//If combined balance limit is exceeded, throw ExceedsCombinedBalanceLimitException
//Should also add a deposit transaction with the opening balance
//SavingsAccount addSavingsAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException
//If combined balance limit is exceeded, throw ExceedsCombinedBalanceLimitException
//Should also add a deposit transaction with the opening balance
//SavingsAccount addSavingsAccount(SavingsAccount savingsAccount) throws ExceedsCombinedBalanceLimitException
//If combined balance limit is exceeded, throw ExceedsCombinedBalanceLimitException
//Should also add a deposit transaction with the opening balance
//CDAccount addCDAccount(CDOffering offering, double openingBalance)
//Should also add a deposit transaction with the opening balance
//CDAccount addCDAccount(CDAccount cdAccount)
//Should also add a deposit transaction with the opening balance


package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CDAccount extends BankAccount {

	Date date;
	private int term;
	CDOffering offering;



	public CDAccount( long accountNumber,Date accountOpenedOn, double balance, double interestRate, int term){
		super(balance, interestRate, accountOpenedOn);
		this.balance = balance;
		this.term = term; 

	}
	public CDAccount(CDOffering offering, double openingBalance){
		super(openingBalance,offering.getInterestRate());
		this.offering = offering;
		this.term = offering.getTerm();
	}
	 


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

//same as Bank account with different of term included therefore on stringOverRide we append the term as well 
	public static CDAccount readFromString(String accountData) throws ParseException, NumberFormatException {
//		int testI = 0;
		System.out.print(accountData);
		String[] testName = accountData.split(",");
		SimpleDateFormat daate = new SimpleDateFormat("dd/MM/yyyy");
//		
		long accountNumber = Long.parseLong(testName[0]);
    	double balance = Double.parseDouble(testName[1]);
    	double interestRate = Double.parseDouble(testName[2]);
    	Date accountOpenedOn = daate.parse(testName[3]);
    	int term = Integer.parseInt(testName[4]);
//    	CDAccount newCDAccount = new CDAccount(interestRate,accountOpenedOn,term);
    	CDAccount newCDAccount= new CDAccount(accountNumber,balance,interestRate,accountOpenedOn,term);
    	return newCDAccount;
    }
	
	public String writeToString() {
		StringBuilder stringOverRide = new StringBuilder();
		stringOverRide.append( writeToString()).append( ",");
		stringOverRide.append(term);
		return stringOverRide.toString();
	}
	
	
	
	
}
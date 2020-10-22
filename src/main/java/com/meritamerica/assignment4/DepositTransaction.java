package com.meritamerica.assignment4;

import java.util.Date;

public class DepositTransaction extends Transaction{

	DepositTransaction(BankAccount targetAccount, double amount, Date transactionDate){
		super(targetAccount, amount, transactionDate);
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.transactionDate = new Date();
		
	}

	
	
}

package com.meritamerica.assignment4;

import java.util.Date;

public class TransferTransaction extends Transaction {

	TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount){
		
		super(sourceAccount,targetAccount, amount);
		sourceAccount = this.sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.transactionDate = new Date();
	}
	
}

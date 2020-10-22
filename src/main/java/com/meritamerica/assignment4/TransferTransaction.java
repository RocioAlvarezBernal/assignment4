package com.meritamerica.assignment4;

import java.util.Date;

public class TransferTransaction extends Transaction {

	TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount, Date transactionDate){
		super(sourceAccount, targetAccount, amount, transactionDate);
		Transaction.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.transactionDate = new Date();
	}
	
}

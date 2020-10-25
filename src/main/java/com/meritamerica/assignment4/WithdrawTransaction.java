package com.meritamerica.assignment4;

import java.util.Date;

public class WithdrawTransaction extends Transaction{
	
	WithdrawTransaction(BankAccount targetAccount, double amount){
		super(targetAccount, amount);
		this.targetAccount = targetAccount;
		this.amount= amount;
		this.transactionDate = new Date();
		}
	
	
	
	}

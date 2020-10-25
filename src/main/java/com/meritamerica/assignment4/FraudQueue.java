package com.meritamerica.assignment4;

import java.util.ArrayList;
import java.util.List;

public class FraudQueue {
	Transaction transaction;
	public List<Transaction> listOfTransaction = new ArrayList<Transaction>();
	
	FraudQueue(){
		
	}
	public void addTransaction(Transaction transaction) {
		listOfTransaction.add(transaction);
	}
	public List <Transaction> getTransaction() {
		return listOfTransaction; 
	}


}

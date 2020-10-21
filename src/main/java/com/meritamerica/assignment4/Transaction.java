package com.meritamerica.assignment4;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;


//public java.util.Date getTransactionDate()   
//public void setTransactionDate(java.util.Date date)
//public String writeToString()
//public static Transaction readFromString(String transactionDataString)
//public abstract void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException
//public boolean isProcessedByFraudTeam()
//public void setProcessedByFraudTeam(boolean isProcessed)
//public String getRejectionReason()
//public void setRejectionReason(String reason)


public class Transaction {
	BankAccount sourceAccount;
	BankAccount TargetAccount;
	double amount;
	Date transactionDate;   //unsure of format
	String reason;
	
	Transaction(String[] stringArray) {
//		
//		this.sourceAccount = stringArray //[0];
//		this.TargetAccount = BankAccount stringArray[1];
//		this.
	}
	
//==========GETTERS/SETTERS===================
	
	public BankAccount getSourceAccount() {
		return sourceAccount;
	}
	public void setSourceAccount(BankAccount sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	public BankAccount getTargetAccount() {
		return TargetAccount;
	}
	public void setTargetAccount(BankAccount targetAccount) {
		TargetAccount = targetAccount;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getTransActionDate() {
		return transactionDate;
	}
	public void setTransActionDate(Date transActionDate) {
		this.transactionDate = transActionDate;
	}
	public String getRejectionReason() {
		return reason;
	}
	public void setRejectionReason(String reason) {
		this.reason = reason;
	}
	
	
	public String writeToString() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		StringBuilder sb = new StringBuilder();
		//toString // append -1
		sb.append(sourceAccount.getAccountNumber());
		sb.append(",");
		sb.append(amount);
		sb.append(",");
		sb.append(dateFormatter.format(transactionDate));
		return sb.toString();
		
	}
	public static Transaction readFromString(String transactionDataString) {
		
		String[] localArray = transactionDataString.split(",");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		long sourceAccount = Long.parseLong(transactionDataString[0]);
		long targetAccount = Long.parseLong(transactionDataString[1]);
		double ammount = Double.parseDouble(transactionDataString[2]);
		Date transationDate = formatter.parse(transactionDataString[3]);
		
		return new Transaction(localArray);
	}
	//public abstract void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException
	//public boolean isProcessedByFraudTeam()
	//public void setProcessedByFraudTeam(boolean isProcessed)
	//public String getRejectionReason()
	//public void setRejectionReason(String reason)

}

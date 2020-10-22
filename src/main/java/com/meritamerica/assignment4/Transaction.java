package com.meritamerica.assignment4;

import java.text.ParseException;
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


public abstract class Transaction {
	BankAccount sourceAccount;
	BankAccount targetAccount;
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
		return targetAccount;
	}
	public void setTargetAccount(BankAccount targetAccount) {
		this.targetAccount = targetAccount;
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
<<<<<<< HEAD
//	Psuedo Code
//		toString // append -1
//		if (sourceAccount == null) {
//			sb.append("-1");
//		}else {
//			sb.append(sourceAccount.getAccountNumber);
//		}
		sb.append(sourceAccount.getAccountNumber());
=======
		if (sourceAccount == null) {
			sb.append("-1");
		}else {
			sb.append(sourceAccount.getAccountNumber());
		}
		sb.append(targetAccount.getAccountNumber());
>>>>>>> be334f3fdde68a738e9fb4eca20c2fceceae2b28
		sb.append(",");
		sb.append(amount);
		sb.append(",");
		sb.append(dateFormatter.format(transactionDate));
		return sb.toString();
	}
<<<<<<< HEAD
	public static Transaction readFromString(String transactionDataString) throws ParseException { // need to catch this exception
		String[] localArray = transactionDataString.split(",");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		
//	Psuedo Code
//		if localArray[0]="-1"{
//				sourceAccount = null;
//		}
//		else {
//			sourceAccount = Long.parseLong(localArray[0]);
//		}
//		long sourceAccount = Long.parseLong(transactionDataString[0]);
		
//are we supposed to use the name of our stingArray here? I think 
		long targetAccount = Long.parseLong(localArray[1]);
		double ammount = Double.parseDouble(localArray[2]);
		Date transationDate = formatter.parse(localArray[3]);
=======
	public static Transaction readFromString(String transactionDataString) throws ParseException {
		String[] localArray = transactionDataString.split(",");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		BankAccount test;
//		Long sourceAccount;

		if (localArray[0].equals("-1")){
			test = null;
		}
		else {
//PlaceHolder to be coded when MeritBank is completed 
//			public static BankAccount getBankAccount(long accountId)
//			Return null if account not found

			test = MeritBank.PlaceHolder(Long.valueOf(localArray[0]));
		}		
		long targetAccount = Long.parseLong(localArray[1]);
		double ammount = Double.parseDouble(localArray[2]);
		Date transationDate = formatter.parse(localArray[3]);
		
		if ([0] == -1) {
			if (valueOf[2] < 0){
				withdraw
			}
		}
>>>>>>> be334f3fdde68a738e9fb4eca20c2fceceae2b28
		
		return new Transaction(localArray);
	}
	//public abstract void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException
	//public boolean isProcessedByFraudTeam()
	//public void setProcessedByFraudTeam(boolean isProcessed)
	//public String getRejectionReason()
	//public void setRejectionReason(String reason)

}

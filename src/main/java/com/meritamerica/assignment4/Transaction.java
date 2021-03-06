package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

public abstract class Transaction {

	BankAccount sourceAccount;
	BankAccount targetAccount;
	double amount;
	Date transactionDate; // unsure of format
	String reason;

//================ TRANSACTION CONSTRUCTORS =============================	
	Transaction(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.transactionDate = new Date();
	}

	Transaction(BankAccount targetAccount, double amount) {
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.transactionDate = new Date();
	}

//========== GETTERS/SETTERS===================

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

	public void setTransActionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
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
		if (sourceAccount == null) {
			sb.append("-1");
		} else {
			sb.append(sourceAccount.getAccountNumber());
		}
		sb.append(",");
		sb.append(targetAccount.getAccountNumber());
		sb.append(",");
		sb.append(amount);
		sb.append(",");
		sb.append(dateFormatter.format(transactionDate));
		return sb.toString();
	}

	// need to catch this exception
	public static Transaction readFromString(String transactionDataString) throws ParseException {
		String[] localArray = transactionDataString.split(",");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		BankAccount test; // placeholder name
//		long thing;
		// conditionals:

		if (localArray[0].equals("-1")) {
			test = null;
		} else {
			test = MeritBank.getBankAccount(Long.valueOf(localArray[0]));
		}
		BankAccount targetAccount = MeritBank.getBankAccount(Long.valueOf(localArray[1]));
		double amount = Double.parseDouble(localArray[2]);
		Date transactionDate = formatter.parse(localArray[3]);

		if (Integer.valueOf(localArray[0]) == -1) {

			if (amount < 0) {
				WithdrawTransaction withdraw = new WithdrawTransaction(targetAccount, amount);
				withdraw.setTransActionDate(transactionDate);
				return withdraw;
			}

			if (amount > 0) {
				DepositTransaction deposit = new DepositTransaction(targetAccount, amount);
				deposit.setTransActionDate(transactionDate);
				return deposit;
			}
		}

		else /* (Integer.valueOf(localArray[0]) != -1) */ {
			TransferTransaction transaction = new TransferTransaction(test, targetAccount, amount);
			transaction.setTransActionDate(transactionDate);
			return transaction;
		}
		return null;
	}
//	 public abstract void process() throws NegativeAmountException;
}


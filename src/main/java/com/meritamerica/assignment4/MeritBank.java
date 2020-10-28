
//Existing futureValue methods that used to call Math.pow() should now call this method
//public static boolean processTransaction(Transaction transaction) throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException

//If transaction does not violate any constraints, deposit/withdraw values from the relevant BankAccounts and add the transaction to the relevant BankAccounts
//If the transaction violates any of the basic constraints (negative amount, exceeds available balance) the relevant exception should be thrown and the processing should terminate
//If the transaction violates the $1,000 suspicion limit, it should simply be added to the FraudQueue for future processing
//public static FraudQueue getFraudQueue()
//public static BankAccount getBankAccount(long accountId)
//Return null if account not found

//static boolean readFromFile(String fileName)
//Should also read BankAccount transactions and the FraudQueue
//static boolean writeToFile(String fileName)
//Should also write BankAccount transactions and the FraudQueue

package com.meritamerica.assignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MeritBank {

	private static CDOffering CDoff[] = new CDOffering[0];
	private static AccountHolder AHNewaccounts[] = new AccountHolder[0];
	static long nextAccountNumber = 0;
	static FraudQueue fraudQueue = new FraudQueue(); // what to do here but we like the way it looks

	public static void addAccountHolder(AccountHolder accountHolder) {

		AccountHolder[] testArray = new AccountHolder[AHNewaccounts.length + 1];
		for (int i = 0; i < testArray.length - 1; i++) {
			testArray[i] = AHNewaccounts[i];
		}
		AHNewaccounts = testArray;
		AHNewaccounts[AHNewaccounts.length - 1] = accountHolder;

	}

	public static AccountHolder[] getAccountHolders() {
		return AHNewaccounts;
	}

	public static CDOffering[] getCDOfferings() {
		return CDoff;
	}

	public static CDOffering getBestCDOffering(double depositAmount) {
		double best = 0.0;
		CDOffering bestOffering = null;
		if (CDoff == null) {
			return null;
		}
		for (CDOffering offering : CDoff) {
			if (recursiveFutureValue(depositAmount, offering.getInterestRate(), offering.getTerm()) > best) {
				bestOffering = offering;
				best = recursiveFutureValue(depositAmount, offering.getInterestRate(), offering.getTerm());
			}
		}
		return bestOffering;
	}

	public static CDOffering getSecondBestCDOffering(double depositAmount) {
		if (CDoff == null) {
			return null;
		}
		CDOffering bestOffering = null;
		double best = 0.0;
		CDOffering secondBestOffering = null;

		for (CDOffering offering : CDoff) {
			if (recursiveFutureValue(depositAmount, offering.getInterestRate(), offering.getTerm()) > best) {
				secondBestOffering = bestOffering;
				bestOffering = offering;
				best = recursiveFutureValue(depositAmount, offering.getInterestRate(), offering.getTerm());
			}
		}
		return secondBestOffering;
	}

	public static void clearCDOfferings() {
		CDoff = null;
	}

	public static void setCDOfferings(CDOffering[] offerings) {
		CDoff = offerings;
	}

	public static long getNextAccountNumber() {
		return nextAccountNumber;
	}

	public static double totalBalances() {
		double total = 0.0;
		for (AccountHolder accounts : AHNewaccounts) {
			total += accounts.getCombinedBalance();
		}
		return total;

	}

	public static boolean readFromFile(String fileName) {
		CDoff = new CDOffering[0];
		setNextAccountNumber(0);
		AHNewaccounts = new AccountHolder[0];
		fraudQueue = new FraudQueue();
//		Map<String, ArrayList> transactionHashMap = new HashMap<String, ArrayList>();
		Set<String> transactions = new HashSet<String>();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {

			setNextAccountNumber(Long.valueOf(bufferedReader.readLine()));
			int offeringNumber = Integer.valueOf(bufferedReader.readLine());
			for (int i = 0; i < offeringNumber; i++) {
				CDoff = Arrays.copyOf(CDoff, CDoff.length + 1);
				CDoff[CDoff.length - 1] = CDOffering.readFromString(bufferedReader.readLine());
			}
			int accountHolderNumber = Integer.valueOf(bufferedReader.readLine());

//			AccountHolder[] newAccountHoldersArr = new AccountHolder[accountHolderNumber];
			for (int a = 0; a < accountHolderNumber; a++) {
				AccountHolder accHolder = AccountHolder.readFromString(bufferedReader.readLine());
				MeritBank.addAccountHolder(accHolder);
				int checkingAccNum = Integer.valueOf(bufferedReader.readLine());
				for (int b = 0; b < checkingAccNum; b++) {
					accHolder.addCheckingAccount(CheckingAccount.readFromString(bufferedReader.readLine()));
					int numbCheckTransactions = Integer.valueOf(bufferedReader.readLine());
					for (int checkingTransaction = 0; checkingTransaction < numbCheckTransactions; checkingTransaction++) {
						transactions.add(bufferedReader.readLine());
					}
				}
				int savingAccNum = Integer.valueOf(bufferedReader.readLine());
				for (int c = 0; c < savingAccNum; c++) {
					accHolder.addSavingsAccount(SavingsAccount.readFromString(bufferedReader.readLine()));
					int numbSavTrans = Integer.valueOf(bufferedReader.readLine());
					for (int savingsTran = 0; savingsTran < numbSavTrans; savingsTran++) {
						transactions.add(bufferedReader.readLine());
					}
				}

				int cdAccNum = Integer.valueOf(bufferedReader.readLine());
				for (int d = 0; d < cdAccNum; d++) {
					accHolder.addCDAccount(CDAccount.readFromString(bufferedReader.readLine()));
					int cdAccTran = Integer.valueOf(bufferedReader.readLine());
					for (int cdTran = 0; cdTran < cdAccTran; cdTran++) {
						transactions.add(bufferedReader.readLine());
					}
				}
			}

			int fraudQueueAlerts = Integer.valueOf(bufferedReader.readLine());
			for (int e = 0; e < fraudQueueAlerts; e++) {
				fraudQueue.addTransaction(Transaction.readFromString(bufferedReader.readLine()));
			}
			for (String transaction : transactions) {
				Transaction newTransactions = Transaction.readFromString(transaction);
				if (newTransactions.getSourceAccount() == null) {
					newTransactions.getTargetAccount().addTransaction(newTransactions);
				} else {
					newTransactions.getSourceAccount().addTransaction(newTransactions);
					newTransactions.getSourceAccount().addTransaction(newTransactions);
				}
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	static boolean writeToFile(String fileName) {
		try {
			FileWriter fileOut = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileOut);
			bufferedWriter.write(String.valueOf(nextAccountNumber));
			bufferedWriter.newLine();
			bufferedWriter.write(String.valueOf(CDoff.length));
			bufferedWriter.newLine();
			for (int i = 0; i < CDoff.length; i++) {
				bufferedWriter.write(CDoff[i].writeToString());
				bufferedWriter.newLine();
			}

			bufferedWriter.write(String.valueOf(AHNewaccounts.length));
			bufferedWriter.newLine();
			for (int a = 0; a < AHNewaccounts.length; a++) {
				bufferedWriter.write(AHNewaccounts[a].writeToString());
				bufferedWriter.newLine();
				bufferedWriter.write(String.valueOf(AHNewaccounts[a].getCheckingAccounts().length));
				bufferedWriter.newLine();
				for (int b = 0; b < AHNewaccounts[a].getCheckingAccounts().length; b++) {
					bufferedWriter.write(AHNewaccounts[a].getCheckingAccounts()[b].writeToString());
					bufferedWriter.newLine();
				}

				bufferedWriter.write(String.valueOf(AHNewaccounts[a].getSavingsAccounts().length));
				bufferedWriter.newLine();
				for (int x = 0; x < AHNewaccounts[a].getSavingsAccounts().length; x++) {
					bufferedWriter.write(AHNewaccounts[a].getSavingsAccounts()[x].writeToString());
					bufferedWriter.newLine();

					bufferedWriter.write(String.valueOf(AHNewaccounts[a].getCDAccounts().length));
					bufferedWriter.newLine();
					for (int n = 0; n < AHNewaccounts[a].getCDAccounts().length; n++) {
						bufferedWriter.write(AHNewaccounts[a].getCDAccounts()[n].writeToString());
						bufferedWriter.newLine();
					}
				}

//			bufferedWriter.newLine();
				bufferedWriter.write(String.valueOf(fraudQueue.getTransaction().size()));
				bufferedWriter.newLine();
				for (int fraud = 0; fraud < fraudQueue.getTransaction().size(); fraud++)
//				bufferedWriter.write(fraudQueue.getTransaction()[fraud].writeToString());
//				bufferedWriter.write(fraudQueue[fraud].get(fraud).writeToString());
					bufferedWriter.write(fraudQueue.getTransaction().get(fraud).writeToString());
				bufferedWriter.newLine();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	static AccountHolder[] sortAccountHolders() {
		Arrays.sort(AHNewaccounts);
		for (AccountHolder modify : AHNewaccounts) {
			System.out.println("Modified arr[] " + modify);

		}
		return AHNewaccounts;
	}

	static void setNextAccountNumber(long accountNumber) {
		nextAccountNumber = accountNumber;

	}

	static BankAccount getBankAccount(long accountId) {//// Return null if account not
		for (AccountHolder item : AHNewaccounts) {
			for (int i = 0; i < item.getCheckingAccounts().length; i++) {
//				if(accountId == AHNewaccounts[i].getCheckingAccounts().
//				if (accountId == getCheckingAccounts[i].getAccountNumber)
				if (accountId == item.getCheckingAccounts()[i].getAccountNumber()) {
					return item.getCheckingAccounts()[i];
				}
			}
			for (int j = 0; j < item.getSavingsAccounts().length; j++) {
				if (accountId == item.getSavingsAccounts()[j].getAccountNumber()) {
					return item.getSavingsAccounts()[j];
				}
			}
			for (int k = 0; k < item.getCDAccounts().length; k++) {
				if (accountId == item.getCDAccounts()[k].getAccountNumber()) {
					return item.getCDAccounts()[k];
				}
			}
		}
		return null;
	}

	public static double recursiveFutureValue(double amount, int years, double interestRate) {
		double newAmount = 0;
		if (years > 0) {
			for (int i = 1; i <= years; i++) {
				newAmount = amount * interestRate;
				years--;
				recursiveFutureValue(newAmount, years, interestRate);
			}
			return newAmount;
		} else {
			return amount;

		}
	}

	public static boolean processTransaction(Transaction transaction)
			throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		BankAccount currentSourceAccount = transaction.getSourceAccount();
		BankAccount currentTargetAccount = transaction.getTargetAccount();

		if (!(currentSourceAccount == null)) { // if there is a current sourceAccount, this transaction is a transfer
			if (transaction.getAmount() <= 0 && transaction.getAmount() < 1000) {
				throw new NegativeAmountException("Cannot transfer negative amount.");
			}
			if (transaction.getAmount() > currentSourceAccount.getBalance()) {
				throw new ExceedsAvailableBalanceException("Insufficient Funds");
			}
			if (transaction.getAmount() > 1000) {
				fraudQueue.addTransaction(transaction);
				throw new ExceedsFraudSuspicionLimitException("Transactions over $1,000 must be reviewed");
			}
			return true;
		}
//WITHDRAW AND/OR DEPOSIT
		if (currentSourceAccount == null) {// if null, this is either withdraw or deposit

			if (transaction instanceof DepositTransaction) {
				if (transaction.getAmount() <= 0) {
					throw new NegativeAmountException("Amount cannot negative amount.");
				}

				if (transaction.getAmount() > 1000) {
					fraudQueue.addTransaction(transaction);
					throw new ExceedsFraudSuspicionLimitException("Transactions over $1,000 must be reviewed");
				}
				return true;

			}
			if (transaction instanceof WithdrawTransaction) {
				if (transaction.getAmount() <= 0) {
					throw new NegativeAmountException("Amount cannot negative amount.");
				}

				if (transaction.getAmount() > 1000) {
					fraudQueue.addTransaction(transaction);
					throw new ExceedsFraudSuspicionLimitException("Transactions over $1,000 must be reviewed");
				}
				if (transaction.getAmount() > currentTargetAccount.getBalance()) {
					throw new ExceedsAvailableBalanceException("Insufficient Funds");
				}
			}
		}
		return false;

	}

	public static FraudQueue getFraudQueue() {
		return fraudQueue;
	}

}

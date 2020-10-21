//public static double recursiveFutureValue(double amount, int years, double interestRate)
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
import java.util.Arrays;

public class MeritBank {
//	private static SavingsAccount savingsAcc[] = new SavingsAccount[0];
//	private static CDOffering checkAcc[] = new CDOffering[0];
	private static CDOffering CDoff[] = new CDOffering[0];
	private static AccountHolder AHNewaccounts[] = new AccountHolder[0];
//	private static CDOffering[] cdOfferings;
//	double totalBalance = 0;
//	public static CDOffering bestCDOffering;
//	static CDOffering secondBestCDOffering;
	static long nextAccountNumber = 0;

	public static void addAccountHolder(AccountHolder accountHolder) {
//		int test = 0;
//		int incr = test + 1;
		AccountHolder[] testArray = new AccountHolder[AHNewaccounts.length + 1];
		for (int i = 0; i < testArray.length - 1; i++) {
			testArray[i] = AHNewaccounts[i];
		}
		AHNewaccounts = testArray;
		AHNewaccounts[AHNewaccounts.length - 1] = accountHolder;
//		test++;
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
			if (futureValue(depositAmount, offering.getInterestRate(), offering.getTerm()) > best) {
				bestOffering = offering;
				best = futureValue(depositAmount, offering.getInterestRate(), offering.getTerm());
			}
		}
		return bestOffering;
	}

//	public static CDOffering getSecondBestCDOfferings(double depositAmount) {
//		double best = 0.0; 
//		CDOffering bestOffering = null;
//		if(CDoff == null) {
//			return null;
//		}
//			for(CDOffering offering :  CDoff) {
//				if(futureValue(depositAmount, offering.getInterestRate(), offering.getTerm()) > best) {
//					bestOffering = offering;
//					best = futureValue(depositAmount, offering.getInterestRate(), offering.getTerm());
//				}
//			}
//		return bestOffering;
//	}
	public static CDOffering getSecondBestCDOffering(double depositAmount) {
		if (CDoff == null) {
			return null;
		}
		CDOffering bestOffering = null;
		double best = 0.0;
		CDOffering secondBestOffering = null;

		for (CDOffering offering : CDoff) {
			if (futureValue(depositAmount, offering.getInterestRate(), offering.getTerm()) > best) {
				secondBestOffering = bestOffering;
				bestOffering = offering;
				best = futureValue(depositAmount, offering.getInterestRate(), offering.getTerm());
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

	public static double futureValue(double presentValue, double interestRate, int term) {
		// expression is located in bankAcount and will call from there
		double futureValueM = presentValue * Math.pow(1 + interestRate, term);
		return futureValueM;
	}
/*psuedo code 
 * read the file a line at at time seting the value of each to an array 
 * 
*/
	static boolean readFromFile(String fileName) {
		CDOffering larry[] = new CDOffering[0];
		try {
			FileReader file = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(file);
			Long nextAccountNumber = Long.valueOf(bufferedReader.readLine());
			int offeringNumber = Integer.valueOf(bufferedReader.readLine());
			for (int i = 0; i < offeringNumber; i++) {
				larry = Arrays.copyOf(larry, larry.length + 1);
				larry[larry.length - 1] = CDOffering.readFromString(bufferedReader.readLine());
			}
			int accountHolderNumber = Integer.valueOf(bufferedReader.readLine());
			AccountHolder[] newAccountHoldersArr = new AccountHolder[accountHolderNumber];
			for (int a = 0; a < accountHolderNumber; a++) {
				AccountHolder accHolder = AccountHolder.readFromString(bufferedReader.readLine());
				int checkingAccNum = Integer.valueOf(bufferedReader.readLine());
				for (int b = 0; b < checkingAccNum; b++) {
					accHolder.addCheckingAccount(CheckingAccount.readFromString(bufferedReader.readLine()));
				}
				int savingAccNum = Integer.valueOf(bufferedReader.readLine());
				for (int c = 0; c < savingAccNum; c++) {
					accHolder.addSavingsAccount(SavingsAccount.readFromString(bufferedReader.readLine()));
				}
				int cdAccNum = Integer.valueOf(bufferedReader.readLine());
				for (int x = 0; x < cdAccNum; x++) {
					accHolder.addCDAccount(CDAccount.readFromString(bufferedReader.readLine()));
				}
				newAccountHoldersArr[a] = accHolder;
			}
			setNextAccountNumber(nextAccountNumber);
			CDoff = larry;
			AHNewaccounts = newAccountHoldersArr;
			file.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}

	}

//		//throw not found 
//	static boolean writeToFile(String fileName) {
//		try {
//			FileWriter fileout = new FileWriter(fileName);
//			BufferedWriter bfferout = new BufferedWriter(fileout);
//			bfferout.write(String.valueOf(nextAccountNumber));
//			bfferout.newLine();
//			bfferout.write(String.valueOf(CDoff.length);
//			bfferout.newLine();
//			//writer.close();
//			//for int i=0; i<=CDoff.length; i++; {
//			//write CDoff[i] array }
//			bfferout.write(String.valueOf(AHNewaccounts.length);
//			bfferout.newLine();
//			for (int b = 0; b<=AHNewaccounts.length; b++) {
//			write AHNewaccounts[b] newline();
//			// create loop to cycle through all accounts and (through other loops) pull savings, checkings and CD
//				return true;
//		}}
//			catch (Exception e) { 
//	            System.out.println(e);
//	            return false;
//		}}
//	
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
				for (int b = 0; b< AHNewaccounts[a].getCheckingAccounts().length; b++) {
					bufferedWriter.write(AHNewaccounts[a].getCheckingAccounts()[b].writeToString());
					bufferedWriter.newLine();
				}
				
				
				bufferedWriter.write(String.valueOf(AHNewaccounts[a].getSavingsAccounts().length));
				bufferedWriter.newLine();
				for (int x = 0; x < AHNewaccounts[a].getSavingsAccounts().length; x++) {
					bufferedWriter.write(AHNewaccounts[a].getSavingsAccounts()[x].writeToString());
					bufferedWriter.newLine();
				}
				
				
				bufferedWriter.write(String.valueOf(AHNewaccounts[a].getCDAccounts().length));
				bufferedWriter.newLine();
				for (int n = 0; n < AHNewaccounts[a].getCDAccounts().length; n++) {
					bufferedWriter.write(AHNewaccounts[a].getCDAccounts()[n].writeToString());
					bufferedWriter.newLine();
				}
			}
			fileOut.close();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

//	public static void clearCDOfferings() {
//		cdOfferings = null;
//	}
//
//	public static void setCDOfferings(CDOffering[] offerings) {
//		cdOfferings = offerings;
//	}
//
//	public static long getNextAccountNumber() {
////after testing it seems this does not print but it meets req
////		long getNextAccountNumber = 99999999 + 1;
////		System.out.println("test acc num " + getNextAccountNumber);
//		return nextAccountNumber;
//	}
//
//	public static double totalBalances() {
//		double start = 0;
//		return start;
//	}

	static AccountHolder[] sortAccountHolders() {
		Arrays.sort(AHNewaccounts);
		for (AccountHolder modify : AHNewaccounts) {
			System.out.println("Modified arr[] " + modify);
//			return a;
//			return AHNewaccounts;
		}
		return AHNewaccounts;
	}

	static void setNextAccountNumber(long accountNumber) {
		nextAccountNumber = accountNumber;

	}

}

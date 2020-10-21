/*class AccountHolder implements Comparable<AccountHolder>
Implement the compareTo(AccountHolder otherAccountHolder) method such that account holders can be sorted by the combined balance of their accounts
String writeToString()
static AccountHolder readFromString(String accountHolderData) throws Exception
Should throw a java.lang.Exception if String cannot be correctly parsed
*/
package com.meritamerica.assignment3;

public class AccountHolder implements Comparable<AccountHolder> {
	private static final double MAX_BALANCE = 250000;
//	double totalBalanceAtThisPoint = 0.0;
//	double checkingBalance = 0.0;
//	double savingsBalance = 0.0;
//	double CDBalanceAmountalance = 0.0;
//	double combinedBalance = 0.0;
//	int CDtest;
//	int CHeckingtest;
//	int Savingstest;
//class variables
	String firstName;
	String middleName;
	String lastName;
	String ssn;

// was not able to reference from BankAccount was under the impression this Would work as a super class 
//or it would be d to in their respected account files 
	CheckingAccount[] addedCheckingAccounts = new CheckingAccount[0];
//	public static CheckingAccount finalCAN = this.CheckingAccount;
//	public CheckingAccount finalCAN = this.finalCAN;
//	public CheckingAccount finalCAN;

	SavingsAccount[] addedsavingsAccounts = new SavingsAccount[0];
//	public SavingsAccount finalSAN;

//	public CDOffering cdOffering = new CDOffering(0, 0);
	CDAccount[] addedCDAccounts = new CDAccount[0];
//	public CDAccount cdAccount = new CDAccount(cdOffering, 0);

	public AccountHolder(String firstName, String middleName, String lastName, String ssn) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;
	}

////	public AccountHolder() {
//empty constructor 
//		private String firstName = "";
//		private String middleName = "";
//		private String lastName = "";
//		private String ssn = "";
////	}

//Getters and setters 
	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getSSN() {
		return ssn;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setSSN(String ssn) {
		this.ssn = ssn;
	}

//	new checking account from addCheckingAccount(CheckingAccount) that creates a new object for the array 

	public CheckingAccount addCheckingAccount(double openingBalance) {
		if (getCheckingBalance() + getSavingsBalance() + openingBalance >= MAX_BALANCE) {
			return null;
		}
		CheckingAccount newAccount = new CheckingAccount(openingBalance, CheckingAccount.interest);
		CheckingAccount[] testChecking = new CheckingAccount[addedCheckingAccounts.length + 1];
		for (int i = 0; i < testChecking.length - 1; i++) {
			testChecking[i] = addedCheckingAccounts[i];
		}
		addedCheckingAccounts = testChecking;
		addedCheckingAccounts[addedCheckingAccounts.length - 1] = newAccount;
		return newAccount;
	}

	public boolean addCheckingAccount(CheckingAccount checkingAccount) {
		try {
			if (checkingAccount.getBalance() + getCheckingBalance() + getSavingsBalance() >= MAX_BALANCE) {
				return false;
			}
			CheckingAccount[] testChecking = new CheckingAccount[addedCheckingAccounts.length + 1];
			for (int i = 0; i < testChecking.length - 1; i++) {
				testChecking[i] = addedCheckingAccounts[i];
			}
			addedCheckingAccounts = testChecking;
			addedCheckingAccounts[addedCheckingAccounts.length - 1] = checkingAccount;
			return checkingAccount != null;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null != null;
		}
	}

//	public double getCheckingBalance() {
//		double total = 0.0;
//		int i;
//		for (i = 0; i < addedCheckingAccounts.length; i++) {
//			total += addedCheckingAccounts[i].getBalance();
//		}
//		return total;
//	}

	// this is the array number of accounts and total amount of all checking
	// accounts
	public CheckingAccount[] getCheckingAccounts() {
		return addedCheckingAccounts;
	}

	public int getNumberOfCheckingAccounts() {
		int numberOfCheckingAccounts = addedCheckingAccounts.length;
		return numberOfCheckingAccounts;
	}

	public double getCheckingBalance() {
		double checkingBalance = 0;
		// loops through the number of accounts to get the sum
		for (int i = 0; i < addedCheckingAccounts.length; i++) {
			checkingBalance = addedCheckingAccounts[i].getBalance() + checkingBalance;
		}
		return checkingBalance;
	}

	public SavingsAccount addSavingsAccount(double openingBalance) {
		if (getSavingsBalance() + getCheckingBalance() + openingBalance >= MAX_BALANCE) {
			return null;
		}
		SavingsAccount newAccount = new SavingsAccount(openingBalance, SavingsAccount.interest);
		SavingsAccount[] testsavings = new SavingsAccount[addedsavingsAccounts.length + 1];
		for (int i = 0; i < testsavings.length - 1; i++) {
			testsavings[i] = addedsavingsAccounts[i];
		}
		addedsavingsAccounts = testsavings;
		addedsavingsAccounts[addedsavingsAccounts.length - 1] = newAccount;
		return newAccount;
	}

	public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount) {
		if (savingsAccount.getBalance() + getCheckingBalance() + getSavingsBalance() >= 250000) {
			System.out.println("Cannot open a new Savings Account because aggregate balance of accounts is to high.");
			return null;
		}
		SavingsAccount[] testsavings = new SavingsAccount[addedsavingsAccounts.length + 1];
		for (int i = 0; i < testsavings.length - 1; i++) {
			testsavings[i] = addedsavingsAccounts[i];
		}
		addedsavingsAccounts = testsavings;
		addedsavingsAccounts[addedsavingsAccounts.length - 1] = savingsAccount;
		return savingsAccount;
	}

	public double getSavingsBalance() {
		double total = 0.0;
		for (SavingsAccount balance : addedsavingsAccounts) {
			total += balance.getBalance();
		}
		return total;

	}

	public SavingsAccount[] getSavingsAccounts() {
		return addedsavingsAccounts;
	}

////the new account will be stored in the array 	
//
//	public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) {
//
//		int currentAmountLength = addedCheckingAccounts.length;
//
//		int checkIncr = CHeckingtest + 1;
//
////		int indexAt = this.addedCheckingAccounts[i];
//
//		totalBalanceAtThisPoint += checkingAccount.getBalance();
//
//		if (totalBalanceAtThisPoint < MAX_BALANCE) {
//
//// makes your index the last since it sets to .length()
//
//			if (CHeckingtest == currentAmountLength) {
// 
//// sets your new made Checking account to a new index in an array
//
//				CheckingAccount[] newCheckingAccounts = new CheckingAccount[/* CHeckingtest++ */ checkIncr];
//
//// for loops through array list with indexes if conditions are met 
//
//				for (int i = 0; i < CHeckingtest; i++) {
//
//// sets the newly created class instance to an index 
//
//					newCheckingAccounts[i] = addedCheckingAccounts[i];
//
//				}
//				addedCheckingAccounts = newCheckingAccounts;
//			}
//			addedCheckingAccounts[CHeckingtest] = checkingAccount;
//
//			CHeckingtest++;
//
//// this will not allow to be added if conditions are not met 
//
//			return null;
//
//		} else
//
//			return null;
//	}

	public CDAccount addCDAccount(CDOffering offering, double openBalance) {
		CDAccount newAccount = new CDAccount(offering, openBalance);
		CDAccount[] testcd = new CDAccount[addedCDAccounts.length + 1];
		for (int i = 0; i < testcd.length - 1; i++) {
			testcd[i] = addedCDAccounts[i];
		}
		addedCDAccounts = testcd;
		addedCDAccounts[addedCDAccounts.length - 1] = newAccount;
		return newAccount;
	}

	public CDAccount addCDAccount(CDAccount cdAccount) {
		CDAccount[] testcd = new CDAccount[addedCDAccounts.length + 1];
		for (int i = 0; i < testcd.length - 1; i++) {
			testcd[i] = addedCDAccounts[i];
		}
		addedCDAccounts = testcd;
		addedCDAccounts[addedCDAccounts.length - 1] = cdAccount;
		return cdAccount;
	}

	public CDAccount[] getCDAccounts() {
		return addedCDAccounts;
	}

////repeat the same as Checking account with Savings account values 
//
//	public SavingsAccount addSavingsAccount(double openingBalance) {
//		finalSAN = new SavingsAccount(openingBalance);
//		addSavingsAccount(finalSAN);
//		return finalSAN;
//	}
//
//	public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount) {
////		int Savingstest = 0;
//		int savingIncr = Savingstest + 1;
//
//		totalBalanceAtThisPoint = totalBalanceAtThisPoint + savingsAccount.getBalance();
//
//		int savingsAccountLength = amountSavingsAccounts.length;
//
//		if (totalBalanceAtThisPoint < MAX_BALANCE) {
//
//			if (Savingstest == savingsAccountLength) {
//
//				SavingsAccount[] newSavingsAccount = new SavingsAccount[savingIncr];
//
//				for (int i = 0; i < Savingstest; i++) {
//
//					newSavingsAccount[i] = amountSavingsAccounts[i];
//				}
//				amountSavingsAccounts = newSavingsAccount;
//			}
//			amountSavingsAccounts[Savingstest] = savingsAccount;
//
//			Savingstest++;
//
//			return null;
//
//		} else {
//
//			return null;
//		}
//
//	}
//

	public int getNumberOfSavingsAccounts() {
		int numberOfSavingsAccounts = addedsavingsAccounts.length;
		return numberOfSavingsAccounts;
	}
//
//	public double getSavingsBalance() {
//		double savingsBalance = 0;
//		for (int i = 0; i < amountSavingsAccounts.length; i++) {
//			savingsBalance = amountSavingsAccounts[i].getBalance() + savingsBalance;
//		}
//		this.savingsBalance = savingsBalance;
//		return savingsBalance;
//	}

//	public CDAccount addCDAccount(CDOffering offering, double openingBalance) {
//		addedCDAccounts = new CDAccount(offering, openingBalance);
//		addCDAccount(addedCDAccounts);
//		return addedCDAccounts;
//	}
//
////if conditions are met CDAccount will be added to array this does not count towards the 250000
//	public CDAccount addCDAccount(CDAccount cdAccount) {
////		int CDtest = 0;
//		int plusOne = CDtest + 1;
//		if (CDtest == addedCDAccounts.length) {
//			CDAccount[] newCDAccount = new CDAccount[plusOne];
//			for (int i = 0; i < CDtest; i++) {
//				newCDAccount[i] = addedCDAccounts[i];
//			}
//			addedCDAccounts = newCDAccount;
//			addedCDAccounts[CDtest] = cdAccount;
////			plusOne
//			CDtest++;
//		}
//		return null;
//	}
//
//	public CDAccount[] getCDAccounts() {
//		return addedCDAccounts;
//	}

	public int getNumberOfCDAccounts() {
		int amountCDAccounts = this.addedCDAccounts.length;
		return amountCDAccounts;
	}

	public double getCDBalance() {
		double total = 0.0;
		for (CDAccount balance : addedCDAccounts) {
			total += balance.getBalance();
		}
		return total;
	}

	public int compareTo(AccountHolder account) {
		if (this.getCombinedBalance() > account.getCombinedBalance()) {
			return 1;
		} else {
			return -1;
		}
	}

	public double getCombinedBalance() {
		double combo = getCDBalance() + getSavingsBalance() + getCheckingBalance();
		return combo;
	}

//	public String toString() {
//		String acountHolderOutput = "Acount Holder Name: " + firstName + " " + middleName + " " + lastName + "\n"
//				+ "SSN: " + ssn + "\n" + "Checkings Balance " + getCheckingBalance1() + "\n" + "Savings Balance "
//				+ getsavingsBalance1() + "\n" + "CD Accounts Balance " + getCDBalanceAmountalance() + "\n"
//				+ "Combined balance " + getCombinedBalance();
//		return acountHolderOutput;
//	}
	public String writeToString() {
		StringBuilder accountHolderData = new StringBuilder();
		accountHolderData.append(firstName).append(",");
		accountHolderData.append(middleName).append(",");
		accountHolderData.append(lastName).append(",");
		accountHolderData.append(ssn);
		return accountHolderData.toString();
	}

	public static AccountHolder readFromString(String accountHolderData) {
		String[] testName = accountHolderData.split(",");
		String firstName = testName[0];
		String middleName = testName[1];
		String lastName = testName[2];
		String ssn = testName[3];
		return new AccountHolder(firstName, middleName, lastName, ssn);
	}
}
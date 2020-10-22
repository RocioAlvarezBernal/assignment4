/*class AccountHolder implements Comparable<AccountHolder>
Implement the compareTo(AccountHolder otherAccountHolder) method such that account holders can be sorted by the combined balance of their accounts
String writeToString()
static AccountHolder readFromString(String accountHolderData) throws Exception
Should throw a java.lang.Exception if String cannot be correctly parsed
*/
package com.meritamerica.assignment4;

public class AccountHolder implements Comparable<AccountHolder> {
	private static final double MAX_BALANCE = 250000;

	String firstName;
	String middleName;
	String lastName;
	String ssn;

// was not able to reference from BankAccount was under the impression this Would work as a super class 
//or it would be d to in their respected account files 
	CheckingAccount[] addedCheckingAccounts = new CheckingAccount[0];


	SavingsAccount[] addedsavingsAccounts = new SavingsAccount[0];

	CDAccount[] addedCDAccounts = new CDAccount[0];


	public AccountHolder(String firstName, String middleName, String lastName, String ssn) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;
	}


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


	public int getNumberOfSavingsAccounts() {
		int numberOfSavingsAccounts = addedsavingsAccounts.length;
		return numberOfSavingsAccounts;
	}


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
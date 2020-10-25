package com.meritamerica.assignment4;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class MeritAmericaBankApp {
	public static void main(String[] args) throws ParseException, ExceedsCombinedBalanceLimitException, ExceedsFraudSuspicionLimitException, NegativeAmountException, ExceedsAvailableBalanceException, FileNotFoundException {
		MeritBank.readFromFile("src/test/testMeritBank_good.txt");	
		MeritBank.writeToFile("src/main/MeritBank.txt");
	//edited
	}
}
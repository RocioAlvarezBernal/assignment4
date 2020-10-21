/*static CDOffering readFromString(String cdOfferingDataString)
Should throw a java.lang.NumberFormatException if String cannot be correctly parsed
String writeToString()*/

package com.meritamerica.assignment4;

public class CDOffering {

	private int term;
	private double interestRate;

	public CDOffering(int term, double interestRate) {
		this.term = term;
		this.interestRate = interestRate;
	}

	public int getTerm() {
		return this.term;
	}

	public double getInterestRate() {
		return this.interestRate;
	}

	static CDOffering readFromString(String cdOfferingDataString) {
//		int testI = 0;
		String[] testName = cdOfferingDataString.split(",");
		int term = Integer.parseInt(testName[0]);
		double interestRate = Double.parseDouble(testName[1]);
		return new CDOffering(term, interestRate);
	}

	String writeToString() {
		StringBuilder testName = new StringBuilder();
		testName.append(term).append(",");
		testName.append(interestRate);
		return testName.toString();

		
		
	}
}

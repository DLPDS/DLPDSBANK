package com.dlpds.bank;


import java.security.SecureRandom;
import java.math.BigInteger;

public class Account {
	private String accNumber;
	private double balance;
	private String currency;
	private SecureRandom random = new SecureRandom();
	
	public Account(double balance, String currency){
		this.setCurrency(currency);
		this.setBalance(balance);	
	}
	
	public Account(double balance, String currency,String accNum){
		this.setCurrency(currency);
		this.setBalance(balance);
		this.setAccNumber(accNum);
	}
	
    private String createAccountNumber() {
    	return new BigInteger(50, random).toString(32);
    }

	public void setBalance(double newbalance) {
		this.balance = newbalance;
	}
						
	public double getBalance() {
		return this.balance;
	}
						
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber() {
		this.accNumber = createAccountNumber();
	}
	
	public void setAccNumber(String accNum) {
		this.accNumber = accNum;
	}
}


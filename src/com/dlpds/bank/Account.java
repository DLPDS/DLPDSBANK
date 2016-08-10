package com.dlpds.bank;


import java.security.SecureRandom;
import java.math.BigInteger;

public class Account {
	private String accNumber;
	private double balance;
	//private User owner;
	private String currency;
	private User accountOwner;
	private SecureRandom random = new SecureRandom();
			
	/*public Account(double balance, User owner) {
		this.balance = balance;
		this.owner = owner;
	}
						
	public Account(User owner) {
		this.owner = owner;
		this.balance = 0;
	}*/
	
	public Account(){
		setAccNumber();
	}
	
    private String createAccountNumber() {
    	return new BigInteger(50, random).toString(32);
    }
						
/*	public User getOwner() {
		return this.owner;
	}*/

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

	private void setAccNumber() {
		this.accNumber = createAccountNumber();
	}

	public User getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(User accountOwner) {
		this.accountOwner = accountOwner;
	}
	

}


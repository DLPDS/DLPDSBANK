package com.dlpds.bank;

import java.util.Date;

public class Transaction {
	private Account fromAcc;
	private Account toAcc;
	private Date dateTime;
	private String username;
	private double amount;
	
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public Account getToAcc() {
		return toAcc;
	}
	public void setToAcc(Account toAcc) {
		this.toAcc = toAcc;
	}
	public Account getFromAcc() {
		return fromAcc;
	}
	public void setFromAcc(Account fromAcc) {
		this.fromAcc = fromAcc;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}

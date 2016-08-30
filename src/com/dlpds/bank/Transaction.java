package com.dlpds.bank;

import java.util.Date;

public class Transaction {
	
	private String fromAcc;
	private String toAcc;
	private Date dateTime;
	private String username;
	private double amount;
	
	public Transaction(){
		
	}
	
	public Transaction(String acc1,String acc2,Date date,double amount){
		this.setFromAcc(acc1);
		this.setToAcc(acc2);
		this.setDateTime(date);
		this.setAmount(amount);
	}
	
	public Date getDateTime() {
		return dateTime;
	}
	
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
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

	public String getFromAcc() {
		return fromAcc;
	}

	public void setFromAcc(String fromAcc) {
		this.fromAcc = fromAcc;
	}

	public String getToAcc() {
		return toAcc;
	}

	public void setToAcc(String toAcc) {
		this.toAcc = toAcc;
	}

}

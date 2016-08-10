package com.dlpds.bank;

import java.util.Date;

public class Transaction {
	private String transactionId;
	private Account fromAcc;
	private Account toAcc;
	private Date dateTime;
	
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
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}

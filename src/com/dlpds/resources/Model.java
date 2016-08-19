package com.dlpds.resources;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model {
	private SimpleStringProperty from;
	private StringProperty to;
	private SimpleStringProperty date;
	private SimpleStringProperty amount;
	
	public Model(String accNum1,String accNum2,String date,String amount){
		this.from=new SimpleStringProperty(accNum1);
		this.to=new SimpleStringProperty(accNum2);
		this.date=new SimpleStringProperty(date);
		this.amount=new SimpleStringProperty(amount);
	};
	

	
	public String getDate() {
		return date.get();
	}
	public void setDate(String date) {
		this.date.set(date);
	}
	public String getTo() {
	return to.get();
	}
	

	public void setTo(String to) {
		this.to.set(to);
		
	}

	public String getAmount() {
		return amount.get();
	}

	public void setAmount(String amount) {
		this.amount.set(amount);
	}



	public String getFrom() {
		return from.get();
	}



	public void setFrom(String from) {
		this.from.set(from);
	}



	public StringProperty to() {
		// TODO Auto-generated method stub
		return to;
	}



	public StringProperty from() {
		// TODO Auto-generated method stub
		return from;
	}
	
	public StringProperty amount() {
		// TODO Auto-generated method stub
		return amount;
	}
	
	public StringProperty date() {
		// TODO Auto-generated method stub
		return date;
	}
}

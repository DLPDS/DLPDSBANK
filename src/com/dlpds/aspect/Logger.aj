package com.dlpds.aspect;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import com.dlpds.bank.Bank;
import com.dlpds.bank.User;

public aspect Logger {
	
	public pointcut login() : execution (* User.registerUser(..));
	after()  :login(){
		Bank bank = Bank.getInstance();
		WriteFile("New Login : Date : " + formatDate() +" Time : " + formatTime());

    	
	}
	
	public void WriteFile(String Log){
		  try {
			    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Login.txt", true)));
			    out.println(Log);
			    out.close();
			} catch (IOException e) {
				System.out.println(e);
			    //exception handling left as an exercise for the reader
			}
	  }

	public String formatDate(){
		Calendar now = Calendar.getInstance();
		String date = now.get(Calendar.YEAR) + "." +  (now.get(Calendar.MONTH) + 1) + "." +now.get(Calendar.DATE);
		return date;
	}
  
  public String formatTime(){
		Calendar now = Calendar.getInstance();
		String time = now.get(Calendar.HOUR)+ "." +now.get(Calendar.MINUTE)+ "." +now.get(Calendar.SECOND);
		return time;
	}
		  




}

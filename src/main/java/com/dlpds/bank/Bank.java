package com.dlpds.bank;

import java.util.ArrayList;

public class Bank {
	private ArrayList<Account> accounts;
	private ArrayList<User> users;
	
	private static Bank bank = new Bank( );

	private Bank(){
		accounts = new ArrayList<Account>();
	    users = new ArrayList<User>();
	}
	   
	   /* Static 'instance' method */
	public static Bank getInstance( ) {
		return bank;
	}
					
	public void addAccount(Account account) {
		accounts.add(account);
	}
						
	public void removeAccount(Account account) {
		accounts.remove(account);
	}
	
	public void addUser(User usr) {
		users.add(usr);
	}
						
	public void removeUsers(User usr) {
		users.remove(usr);
	}
	
	public void transfer(String from, String to, double amount){
		//from.debit(amount);
	    //to.credit(amount);
		Account fromAcc;
		Account toAcc;
		fromAcc=findAccount(from);
		toAcc=findAccount(to);
		if (fromAcc!=null && toAcc!=null){
			fromAcc.setBalance(fromAcc.getBalance()-amount);
			toAcc.setBalance(toAcc.getBalance()+amount);
		}
	}
	
	private Account findAccount(String accNum){
		for (int x=0;x<accounts.size();x++){
			if(accounts.get(x).getAccNumber()==accNum){
				return accounts.get(x);
			}
		}
		return null;
	}
	
	public User findUser(String usrName){
		for (int x=0;x<users.size();x++){
			System.out.println(users.get(x).getUname());
			if(users.get(x).getUname().equals(usrName)){
				return users.get(x);
			}
		}
		return null;
	}
	
}

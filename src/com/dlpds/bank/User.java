package com.dlpds.bank;

import java.util.ArrayList;

public class User {
	private String NIC;
	private String name;
	private String address;
	private String uname;
	private String password;
	private ArrayList<Account> accounts;
	private boolean isRegister = false;
	private boolean isLogin = false;
	public Bank bankOfDLPDS=Bank.getInstance();
	
					
	public User() {
		
	}
	
	public boolean registerUser(String name, String NIC, String address, String uname, String password){
		try{
			this.setName(name);
			this.setAddress(address);
			this.setNIC(NIC);
			this.setUname(uname);
			this.setPassword(password);
			setAccounts(new ArrayList<Account>());
			bankOfDLPDS.addUser(this);
			this.setRegister(true);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public User login(String uname, String password){
		User currentUser=bankOfDLPDS.findUser(uname);
		if (currentUser!=null && currentUser.getPassword().equals(password)){
			currentUser.setLogin(true);
			System.out.println(currentUser.isLogin);
			return currentUser;
		}
		return null;
	}
	
	public String createAccount(double balance, String currency){
		Account myAccount = new Account();
		myAccount.setAccountOwner(this);
		myAccount.setCurrency(currency);
		myAccount.setBalance(balance);
		bankOfDLPDS.addAccount(myAccount);
		accounts.add(myAccount);
		return myAccount.getAccNumber();
		
	}
	
	public void logout(){
		this.setLogin(false);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	public String getNIC() {
		return NIC;
	}

	public void setNIC(String nIC) {
		NIC = nIC;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

	public boolean isRegister() {
		return isRegister;
	}

	public void setRegister(boolean isRegister) {
		this.isRegister = isRegister;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

}

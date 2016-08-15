package com.dlpds.bank;

import java.util.ArrayList;

import com.dlpds.resources.Operations;

public class User {
	private String NIC;
	private String name;
	private String secondName;
	private String gender;
	private String dob;
	private String phoneNumber;
	private String address;
	private String email;
	private String uname;
	private String password;
	private ArrayList<Account> accounts;
	private boolean isRegister = false;
	private boolean isLogin = false;
	public Bank bankOfDLPDS=Bank.getInstance();
	
					
	public User() {
		
	}
	
	public User(String name,String secondName, String NIC, String gender,String dob,String address,String phoneNub,String email, String uname, String password) {
		try{
			this.setName(name);
			this.setSecondName(secondName);
			this.setAddress(address);
			this.setNIC(NIC);
			this.setGender(gender);
			this.setDob(dob);
			this.setUname(uname);
			this.setEmail(email);
			this.setPhoneNumber(phoneNub);
			this.setPassword(password);
			this.setRegister(true);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public boolean registerUser(){
		try{
			setAccounts(new ArrayList<Account>());
			bankOfDLPDS.addUser(this);
			
			Operations operations=new Operations();
			if(operations.registerUser(this,createAccount(1000.00, "Rs"))){
				return true;
			}
			else{
				return false;
			}
			
			/*this.setRegister(true);
			return true;*/
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
	
	public Account createAccount(double balance, String currency){
		Account myAccount = new Account(balance,currency);
		myAccount.setAccNumber();
		bankOfDLPDS.addAccount(myAccount);
		accounts.add(myAccount);
		return myAccount;
		
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

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

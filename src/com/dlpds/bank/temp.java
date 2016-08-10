package com.dlpds.bank;

import java.util.Scanner;

public class temp {
	public static void main(String[] args){
		
		Scanner scanner = new Scanner(System.in);
		String mainoption;
		int mainoptionint;
		System.out.println("Enter Options Login Register");
		System.out.println("No '1'for user registration");
		System.out.println("No '2'for Login");
		System.out.println("No '3'for Money Transfer to other accounts");
		System.out.println("No '4'for Account Inquiries");
		System.out.println("No '5'for Account History Inquiries");
		System.out.println("No '6'for Personal profile management");
		System.out.println("No '7'for Contact bank ");
		System.out.println("No '8'for Create New Bank Account ");
		System.out.println("No '9'for Exit the system ");
		System.out.println("No '10'for log out from the system ");
		User newUser = null;
		
		while(true){
		    System.out.println("Enter");
		    mainoption = scanner.next();
		    try {
		        mainoptionint = Integer.parseInt(mainoption);
		        if(mainoptionint==9){
		        	System.exit(0);
		        }
		        else if(mainoptionint==1){
		        	newUser = new User();
		        	System.out.println("Please enter your name");
		        	String name = scanner.next();
		        	System.out.println("Please enter your NIC");
		        	String nic = scanner.next();
		        	System.out.println("Please enter your mail address");
		        	String mail = scanner.next();
		        	System.out.println("Please enter your user name");
		        	String username = scanner.next();
		        	System.out.println("Please enter your password");
		        	String pwd = scanner.next();
		        	if(newUser.registerUser(name,nic,mail,username,pwd)){
		        		continue;
		        	}else{
		        		System.out.println("Error Occured");
		        	}
		        	
		        	//newUser = new User();
		        	//newUser.registerUser("Amali", "912810046v", "tcdlpds", "amali", "123");
		        }
		        else if(mainoptionint==2){
		        	newUser = new User();
		        	System.out.println("Please enter your username");
		        	String un = scanner.next().trim();
		        	System.out.println("Please enter your password");
		        	String pwd = scanner.next().trim();
		        	System.out.println(pwd.equals("123"));
		        	newUser=newUser.login(un, pwd);
		        	if(newUser!=null){ //correct here. make a class called signin
	        		    System.out.println("User successfully loged in");
		        	}else{
		        		System.out.println("You are not registered user");
		        	}
		        	
		        }
		        else if(mainoptionint==3){
		        	if(newUser.isLogin()){
		        		//Transaction trans=new Transaction();
		        		System.out.println("Please enter your Account Number");
			        	String from = scanner.next();
		        		System.out.println("Please enter Receivered Account Number");
			        	String to = scanner.next();
			        	
			        	System.out.println("Please enter Amount of transfer");
			        	String amount = scanner.next();
		        		Bank bank=Bank.getInstance();
		        		bank.transfer(from, to, Double.parseDouble(amount));
		        	}else{
		        		System.out.println("Please Login");
		        	}
		        }
		        else if(mainoptionint==4){
		        	System.exit(0);
		        }
		        else if(mainoptionint==5){
		        	System.exit(0);
		        }
		        else if(mainoptionint==6){
		        	System.exit(0);
		        }
		        else if(mainoptionint==7){
		        	System.exit(0);
		        }
		        else if(mainoptionint==8){
		        	if(newUser.isLogin()){
		        		String accoutNum=newUser.createAccount(500.00, "Rs");
	        		    System.out.println(accoutNum);
		        	}else{
		        		System.out.println("Please logine");
		        	}
		        	
		        }
		        else if(mainoptionint==10){
		        	if(newUser.isLogin()){
		        		newUser.logout();
		        	}
		        }
		        
		        else{
		        	System.exit(-1);
		        }
		        //System.exit(0);
		    } catch (Exception e) {
		    	System.exit(-1);
		    }
		}
	}
		
		/*System.out.println("*** Creating account 1");
		BankAccount acc1 = new BankAccount(1);
		acc1.credit(150);
		bankAccounts.put(acc1.getAccountNumber(), acc1);
		
		System.out.println("*** Creating account 2");
		BankAccount acc2 = new BankAccount(2);
		acc2.credit(230);
		bankAccounts.put(acc2.getAccountNumber(), acc2);
		
		System.out.println("*** Balance acount 1: " + acc1.getBalance());
		System.out.println("*** Balance acount 2: " + acc2.getBalance());
		
		//Transfer some money
		System.out.println("*** Transfer 50 from account 1 to account 2");
		transfer(acc1, acc2, 50);
		
		System.out.println("*** Balance acount 1: " + acc1.getBalance());
		System.out.println("*** Balance acount 2: " + acc2.getBalance());
		   }*/
}

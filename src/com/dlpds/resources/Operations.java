package com.dlpds.resources;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dlpds.bank.Account;
import com.dlpds.bank.User;

public class Operations {

	public Statement getStatement() {
		try {
			Statement myStam = DBConnection.getConnection().createStatement();
			return myStam;
		} catch (Exception e) {
			return null;
		}

	}

	public ResultSet executeQuery(Statement myStam, String query) {
		try {
			ResultSet myRs = myStam.executeQuery(query);
			return myRs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int dataManipulateQuery(Statement myStam, String query) {
		try {
			int result = myStam.executeUpdate(query);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public boolean validLogin(String uname, String pwd) {
		try {
			System.out.println(uname);
			String query = "SELECT password FROM signin_details where Customer_username='" + uname + "'";
			Statement myStam = getStatement();
			ResultSet myRs = executeQuery(myStam, query);
			System.out.println("Paasword coming");
			String dbpwd = null;
			while (myRs.next()) {
				System.out.println("value");
				System.out.println(myRs.getString("password"));
				dbpwd = myRs.getString("password");
			}
			if (pwd.equals(dbpwd)) {
				initializeApp(dbpwd);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;

		}
	}

	private void initializeApp(String pwd) {
		// TODO Auto-generated method stub
		try {
			String query = "SELECT first_name,last_name,nid,sex,birthday,address,phone_no,email,username FROM internet_bank.customer";
			Statement myStam = getStatement();
			ResultSet myRs = executeQuery(myStam, query);
			String firstName=null;
			String secondName=null;
			String nid=null;
			String gender=null;
			String dob=null;
			String address=null;
			String phoneNumber=null;
			String email=null;
			String uname=null;
			
			String accNumber=null;
			double balance=0.0;
			int currencyid=0;
			
			while (myRs.next()) {	
				firstName=myRs.getString("first_name");
				secondName=myRs.getString("last_name");
				nid=myRs.getString("nid");
				gender=myRs.getString("sex");
				dob=myRs.getString("birthday");
				address=myRs.getString("address");
				phoneNumber=myRs.getString("phone_no");
				email=myRs.getString("email");
				uname=myRs.getString("username");
			}
			User usr=new User(firstName,secondName,nid,gender,dob,address,phoneNumber,email,uname);
			usr.setLogin(true);
			System.out.println(usr.isLogin()+" New user logged");
			
			String query1 = "SELECT account_num,balance,Currency_id FROM internet_bank.account where Customer_username='"+uname+"'";
			Statement myStam1 = getStatement();
			ResultSet myRs1 = executeQuery(myStam1, query1);
			while (myRs1.next()) {	
				accNumber=myRs1.getString("account_num");
				balance=Double.parseDouble(myRs1.getString("balance"));
				currencyid=Integer.parseInt(myRs1.getString("Currency_id"));
			}
			System.out.println(accNumber);
			System.out.println(balance);
			System.out.println(currencyid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public boolean registerUser(User user, Account acc) {
		try {

			String query = "INSERT INTO customer (`first_name`, `last_name`, `nid`, `sex`, `birthday`, `address`, `phone_no`, `email`, `username`, `Bank_id`) VALUES ('"
					+ user.getName() + "','" + user.getSecondName() + "','" + user.getNIC() + "','" + user.getGender()
					+ "','" + user.getDob() + "','" + user.getAddress() + "','" + user.getPhoneNumber() + "','"
					+ user.getEmail() + "','" + user.getUname() + "'," + " '1')";
			Statement myStam = getStatement();
			int result = dataManipulateQuery(myStam, query);
			System.out.println("Paasword coming" + user.getGender());
			System.out.println(result);
			if (result != -1) {
				if (addToSigninTable(user.getUname(), user.getPassword()) != -1
						&& addToAccountTable(acc, user)) {
					
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}

	private boolean addToAccountTable(Account acc, User user) {
		// TODO Auto-generated method stub

		try {
			Statement myStam1 = getStatement();
			Statement myStam2 = getStatement();
			Statement myStam3 = getStatement();
			String query1 = "SELECT id FROM internet_bank.currency where name='" + acc.getCurrency() + "'";
			String query2 = "SELECT id FROM internet_bank.bank where name='Bank of DLPDS'";
			ResultSet myRs1 = executeQuery(myStam1, query1);
			ResultSet myRs2 = executeQuery(myStam2, query2);
			System.out.println("Paasword coming");
			int currencyId = 0;
			int bankId = 0;
			while (myRs1.next() ) {
				currencyId = myRs1.getInt("id");
			}
			
			while (myRs2.next()) {
				bankId = myRs2.getInt("id");
			}

			if (currencyId != 0 && bankId != 0) {
				String query3 = "INSERT INTO `internet_bank`.`account` (`account_num`, `balance`,`Currency_id`,`Customer_username`,`Customer_Bank_id1`) VALUES ('"
						+ acc.getAccNumber() + "','" + acc.getBalance() + "','" + currencyId + "','" + user.getUname()
						+ "','" + bankId + "')";
				int result = dataManipulateQuery(myStam3, query3);
				if (result != -1) {
					return true;
				} else {
					return false;
				}

			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public int addToSigninTable(String uname, String pwd) {
		try {
			Statement myStam = getStatement();
			String query = "INSERT INTO `internet_bank`.`signin_details` (`password`, `Customer_username`) VALUES ('"
					+ pwd + "','" + uname + "')";
			int result = dataManipulateQuery(myStam, query);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;

		}
	}

}

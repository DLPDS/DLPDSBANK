package com.dlpds.resources;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;

		}
	}

	public boolean registerUser(User user) {
		try {

			String query = "INSERT INTO customer (`first_name`, `last_name`, `nid`, `sex`, `birthday`, `address`, `phone_no`, `email`, `username`, `Bank_id`) VALUES ('"
					+ user.getName() + "','" + user.getSecondName() + "','" + user.getNIC() + "','" + user.getGender()
					+ "','" + user.getDob() + "','" + user.getAddress() + "','" + user.getPhoneNumber() + "','"
					+ user.getEmail() + "','" + user.getUname() + "'," + " '1')";
			Statement myStam = getStatement();
			int result = dataManipulateQuery(myStam, query);
			System.out.println("Paasword coming"+user.getGender());
			System.out.println(result);
			if (result != -1) {
				if(addToSigninTable(user.getUname(), user.getPassword())!=-1){
					return true;
				}else{
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
	
	public int addToSigninTable(String uname,String pwd) {
		try {
			String query ="INSERT INTO `internet_bank`.`signin_details` (`password`, `Customer_username`) VALUES ('"+pwd+"','"+uname+"')";
			Statement myStam = getStatement();
			int result = dataManipulateQuery(myStam, query);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;

		}
	}
	
	
}

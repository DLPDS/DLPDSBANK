package com.dlpds.resources;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Operations {
	public boolean validLogin(String uname,String pwd) {
		try {
			System.out.println(uname);
			String query="SELECT password FROM signin_details where Customer_username='"+uname+"'";
			Statement myStam = getStatement();
			ResultSet myRs=executeQuery(myStam, query);
			System.out.println("Paasword coming");
			//System.out.println(myRs.getString("password"));
			String dbpwd=null;
			while(myRs.next()){
				System.out.println("value");
				//myrs.getString(1);
				System.out.println(myRs.getString("password"));
				dbpwd=myRs.getString("password");
			}
			if(pwd.equals(dbpwd)){
				return true;	
			}
			else{
				return false;
			}
		} catch (Exception e) {
			return false;

		}
	}
	
	public Statement getStatement(){
		try{
			Statement myStam = DBConnection.getConnection().createStatement();
			return myStam;
		}catch(Exception e){
			return null;
		}
		
	}
	
	public ResultSet executeQuery(Statement myStam,String query){
		try{
			ResultSet myRs = myStam.executeQuery(query);
			return myRs ;
		}catch(SQLException e){
			return null;
		}
	}
}

package com.dlpds.resources;

import java.sql.*;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Statement myStam=DBConnection.getConnection().createStatement();
			ResultSet myrs=myStam.executeQuery("select * from Customer");
			System.out.println(myrs);
			while(myrs.next()){
				System.out.println("value");
				//myrs.getString(1);
				System.out.println(myrs.getString("first_name"));
			}
		}catch(Exception e){
			e.printStackTrace();;
		}

	}

}

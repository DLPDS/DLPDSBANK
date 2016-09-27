package com.dlpds.resources;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBConnection {
	static String url = "jdbc:mysql://127.0.0.1:3306/Internet_Bank";
    static String username = "root";
    static String password = "root";
    static Connection con = null;
    
    private DBConnection(){
    	
    }
    
    public static Connection getConnection() throws SQLException{
            con = (Connection)DriverManager.getConnection(url, username, password);
            System.out.println("Connected!");
            return con;
    }

}

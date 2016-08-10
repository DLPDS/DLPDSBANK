package com.dlpds.ui;

import com.dlpds.bank.User;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
    @Override 
    public void start(Stage primaryStage) throws Exception{
    	
	    Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
	    primaryStage.setTitle("Welcome");
	    primaryStage.setScene(new Scene(root));
	    primaryStage.show();
        
    }
    
    public static void main(String[] args) {
    	createAccounts();
        Application.launch(args);
        
    }
    
    public static void createAccounts(){
    	User newUser = new User();
    	newUser.registerUser("Amali","Osadi","a@gmail.com", "angomali","ango");
		newUser=newUser.login("angomali", "ango");
		System.out.println(newUser.createAccount(1000, "Rs"));
		
		}
    
}

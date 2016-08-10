package com.dlpds.ui;

import java.io.IOException;

import com.dlpds.bank.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	
	private User currentUser = null;
	
	@FXML
	private TextField uname;
	@FXML
	private TextField pwd;
	@FXML
	private Button login;
	@FXML
	private Button register;
	
	
	public LoginController(){
		System.out.println("New login object created");
	}
	
	@FXML
	protected void initialization(User usr){
		currentUser=usr;
	}
	
	public void changeWindow(String fxmlFile,Button button,String title) {
		// Decalaration of Variables
	    final Stage stage, stage1;
	    FXMLLoader pane;
	    Parent taskselectwindow;

	 // get a handle to the stage
	    stage = (Stage) button.getScene().getWindow();
	    // do what you have to do
	    stage.close();
	    pane = new FXMLLoader(getClass().getResource(fxmlFile));
	    try {
			taskselectwindow = (Parent) pane.load();
			stage1 = new Stage();
		    stage1.setScene(new Scene(taskselectwindow));
		    stage1.setTitle(title);
		    stage1.show();
		    
		    if(fxmlFile.equals("Register.fxml")){
		    	RegisterController controller = pane.<RegisterController>getController();
		    	controller.initialization(currentUser);
				System.out.println("View changed to register");
		    }else if(fxmlFile.equals("MainUI.fxml")){
		    	MainController controller = pane.<MainController>getController();
		    	controller.initialization(currentUser);
				System.out.println("View changed to main");		    	
		    }
		    
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@FXML
	public void loginButtonAction(ActionEvent event){
		User newUser = new User();
		currentUser=newUser.login(uname.getText(), pwd.getText());
    	if(currentUser!=null){ //correct here. make a class called signin
			changeWindow("MainUI.fxml",login,"Bank of DLPDS");
    	}else{
    		displayAleart("Login Error","Please Provide Valid Credentials");
    	}
	}
	
	@FXML
	public void registerButtonAction(ActionEvent event){
		System.out.println("Enter Options Login Register");
		changeWindow("Register.fxml",register,"Registration");
	}
	
	private void displayAleart(String headerText,String content){
		Alert alert = new Alert(AlertType.WARNING);
        //alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Warning");
        alert.setHeaderText(headerText);
        alert.setContentText(content);

        alert.showAndWait();
	}

}

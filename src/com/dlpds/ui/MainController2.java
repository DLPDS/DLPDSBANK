package com.dlpds.ui;

import java.io.IOException;

import com.dlpds.bank.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainController2 {
	
	private User currentUser = null;
	
	@FXML
	private TextField uname;
	@FXML
	private TextField pwd;
	@FXML
	private Pane leftPane;
	@FXML
	private Pane content;
	@FXML
	private Pane rightPane;
	@FXML
	private Pane loginArea;
	@FXML
	private Pane logoutArea;
	@FXML
	private VBox regfxml;
	@FXML
	private TextField regFName;
	@FXML
	private TextField regSName;
	@FXML
	private TextField regNIC;
	@FXML
	private TextField regMail;
	@FXML
	private TextField regUname;
	@FXML
	private TextField regPwd;
	@FXML
	private TextField regCPwd;
	@FXML
	private Button logoutButton;
	
	public MainController2(){
		System.out.println("New object created");
		//logoutArea.managedProperty().bind(logoutArea.visibleProperty());
		//logoutArea.setVisible(false);
	}
	
	@FXML
	protected void initialization(User usr){
		currentUser=usr;
	}
	
	public void changeView(String fxmlFile,Pane pane) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
		try {
			pane.getChildren().add(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainController2 controller = loader.<MainController2>getController();
		controller.initialization(currentUser);
		System.out.println("View changed");
	}
	
	@FXML
	public void loginButtonAction(ActionEvent event){
		User newUser = new User();
		currentUser=newUser.login(uname.getText(), pwd.getText());
    	if(currentUser!=null){ //correct here. make a class called signin
    		//loginArea.getChildren().clear();
    		loginArea.managedProperty().bind(loginArea.visibleProperty());
    		loginArea.setVisible(false);
    		logoutArea.managedProperty().bind(logoutArea.visibleProperty());
    		logoutArea.setVisible(true);
    		leftPane.getChildren().clear();
			//changeView("logout.fxml",loginArea);
			changeView("Options.fxml",leftPane);
    	}else{
    		displayAleart("Login Error","Please Provide Valid Credentials");
    	}
	}
	
	@FXML
	public void registerButtonAction(ActionEvent event){
		System.out.println("Enter Options Login Register");
		leftPane.getChildren().clear();
		changeView("Register.fxml",leftPane);
	}
	
	@FXML
	public void signupButtonAction(ActionEvent event){
		User newUser = new User();
		if(newUser.registerUser(regFName.getText(),regNIC.getText(),regMail.getText(),regUname.getText(),regPwd.getText())){
			regfxml.getChildren().clear();
		}		
	}
	
	@FXML
	public void logoutButtonAction(ActionEvent event){
		if(currentUser!=null && currentUser.isLogin()){
			currentUser.logout();
			//loginArea.getChildren().clear();
    		//leftPane.getChildren().clear();
			System.out.println(logoutButton);
			System.out.println(leftPane);
			System.out.println(regfxml);
    	}
		
		if(currentUser==null){
			System.out.println("Current User is null");
		}
		
		
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

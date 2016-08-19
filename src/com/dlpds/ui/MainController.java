package com.dlpds.ui;

import java.io.IOException;

import com.dlpds.bank.Bank;
import com.dlpds.bank.User;
import com.dlpds.resources.Operations;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {
	
	private User currentUser = null;
	
	@FXML
	private Pane leftPane;
	@FXML
	private Pane content;
	@FXML
	private Pane rightPane;
	@FXML
	private Button transferMoney;
	@FXML
	private Button accountInq;
	@FXML
	private Button accountHist;
	@FXML
	private Button update;
	@FXML
	private Button contactBank;
	@FXML
	private Button logout;
	@FXML
	private TextField initAmount;
	@FXML
	private TextField transAmount;
	@FXML
	private TextField receAccNum;
	@FXML
	private TextField transAccNum;
	@FXML
	private Button transfer;
	@FXML
	private ChoiceBox currency;
	
	@FXML
	private TextField accNum;
	@FXML
	private TextField balance;
	@FXML
	private Button confirm;
	@FXML
	private VBox changingVBox;
	
	
	public MainController(){
		System.out.println("New object created");
	}
	
	@FXML
	protected void initialization(User usr){
		currentUser=usr;
	}
	
	public void changeView(String fxmlFile,Pane pane) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
		try {
			pane.getChildren().clear();
			pane.getChildren().add(loader.load());
			MainController controller = loader.<MainController>getController();
			controller.initialization(currentUser);
			System.out.println("View changed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void changeWindow(String fxmlFile, Button button, String title) {
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
			LoginController controller = pane.<LoginController>getController();
			controller.initialization(currentUser);
			System.out.println("View changed to login");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void logoutButtonAction(ActionEvent event){
		if(currentUser!=null && currentUser.isLogin()){
			currentUser.logout();
			System.out.println("User Logout ");
			changeWindow("Login.fxml",logout,"Login");
    	}
		
		if(currentUser==null){
			System.out.println("Current User is null");
		}
	}
	
	public void updateButtonAction(ActionEvent event){
		if(currentUser!=null && currentUser.isLogin()){
			changeView("UpdateInfo.fxml", content);
		}
	}
	
	public void aiButtonAction(ActionEvent event){
		if(currentUser!=null && currentUser.isLogin()){
			changeView("AccountDetails.fxml", content);
			
		}
	}
	
	public void doChanges(){
		
		if(currentUser!=null && currentUser.isLogin()){
			Operations opp=new Operations();
			String[] details=opp.getAccDetails(currentUser.getUname());
			if(details!=null){
				accNum.setText(details[0]);
				balance.setText(details[1]);
				confirm.setText("Ok");
			}else{
				displayAleart("Warning", "Database Error");
			}
			
		}else{
			displayAleart("Warning", "Internal Error");
		}
	}
	
	public void confirmButtonAction(){
		if(confirm.getText().equals("DISPLAY")){
			doChanges();
		}else{
			changingVBox.getChildren().clear();
		}
		
	}
	
	public void ahiButtonAction(ActionEvent event){
		if(currentUser!=null && currentUser.isLogin()){
			changeView("History.fxml", content);
		}
	}
	
	public void contactBankButtonAction(ActionEvent event){
		if(currentUser!=null && currentUser.isLogin()){
			changeView("SendMessage.fxml", content);
		}
	}
	
	public void transferMoneyButtonAction(ActionEvent event){
		if(currentUser!=null && currentUser.isLogin()){
			changeView("MoneyTransfer.fxml", content);
		}
	}
	
	public void transferButtonAction(ActionEvent event){
		if(currentUser.isLogin()){
    		//Transaction trans=new Transaction();
        	System.out.println("Please enter Amount of transfer "+transAccNum.getText());
    		Bank bank=Bank.getInstance();
    		bank.transfer(transAccNum.getText(),receAccNum.getText() , Double.parseDouble(transAmount.getText()));
    	}else{
    		System.out.println("Please Login");
    	}
	}
	
	public void displayAleart(String headerText,String content){
		Alert alert = new Alert(AlertType.WARNING);
        //alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Warning");
        alert.setHeaderText(headerText);
        alert.setContentText(content);

        alert.showAndWait();
	}
	
	public void displaySuccessAleart(String headerText,String content){
		Alert alert = new Alert(AlertType.INFORMATION);
        //alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Success");
        alert.setHeaderText(headerText);
        alert.setContentText(content);

        alert.showAndWait();
	}

}

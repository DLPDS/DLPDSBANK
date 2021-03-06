package com.dlpds.ui;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import com.dlpds.bank.Account;
import com.dlpds.bank.Transaction;
import com.dlpds.bank.User;
import com.dlpds.resources.Model;
import com.dlpds.resources.Operations;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
	@FXML
	private VBox changingVBox2;
	@FXML
	private VBox changingVBox3;
	@FXML
	private VBox changingVBox4;
	@FXML
	private VBox changingVBox5;

	@FXML
	private TextArea textMessage;
	@FXML
	private Button sendButton;
	@FXML
	private DatePicker dob;
	
	@FXML
	private Button displayButton;
	@FXML
	private TextField displayBalance;
	@FXML
    private TableView<Model> transactionTable;
	@FXML
	private TableColumn<Model, String> date;
	@FXML
	private TableColumn<Model, String> from;
	@FXML
	private TableColumn<Model, String> to;
	@FXML
	private TableColumn<Model, String> amount;
	
	@FXML
	private TextField firstName;
	@FXML
	private TextField secondName;
	@FXML
	private TextField nic;
	@FXML
	private TextField mail;
	@FXML
	private TextField userName;
	@FXML
	private TextField pwd;
	@FXML
	private TextField pwd2;
	@FXML
	private Button updateButton;
	

	public MainController() {
	}

	@FXML
	protected void initialization(User usr) {
		currentUser = usr;
	}

	public void changeView(String fxmlFile, Pane pane) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
		try {
			pane.getChildren().clear();
			pane.getChildren().add(loader.load());
			MainController controller = loader.<MainController>getController();
			controller.initialization(currentUser);
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void logoutButtonAction(ActionEvent event) {
		if (currentUser != null && currentUser.isLogin()) {
			currentUser.logout();
			changeWindow("Login.fxml", logout, "Login");
		}else {
			displayAleart("Warning", "You are not logged in");
		}
	}

	public void updateButtonAction(ActionEvent event) {
		if (currentUser != null && currentUser.isLogin()) {
			changeView("UpdateInfo.fxml", content);
		}
	}
	
	public void updateUser(){
		String currentUname = currentUser.getUname();
		if (updateButton.getText().equals("DISPLAY")) {
			showUserDetails();
		} else if(updateButton.getText().equals("Update")){
			//changingVBox5.getChildren().clear();
			String fName=firstName.getText();
			String sName = secondName.getText();
			String nicNo = nic.getText();
			String e_mail=mail.getText();
			String uname=userName.getText();
			String password = pwd.getText();
			String password2 = pwd2.getText();
			if((!password.equals("")|| !password2.equals("")) && password.equals(password2)){
				System.out.println(currentUname);
				if(currentUser.updateUser(fName,sName,nicNo,e_mail,uname,password,currentUname)){
					displaySuccessAleart("Success", "Profile Details Updated");
					changingVBox5.getChildren().clear();
				}else{
					displayAleart("Error", "Database Error");
				}
				
			}else{
				displayAleart("Error", "password mismatched");
			}
			
		}
	}
	
	public void showUserDetails(){
		firstName.setText(currentUser.getName());
		secondName.setText(currentUser.getSecondName());
		nic.setText(currentUser.getNIC());
		mail.setText(currentUser.getEmail());
		userName.setText(currentUser.getUname());
		updateButton.setText("Update");
		
	}

	public void aiButtonAction(ActionEvent event) {
		if (currentUser != null && currentUser.isLogin()) {
			changeView("AccountDetails.fxml", content);

		}
	}

	public void doChanges() {

		if (currentUser != null && currentUser.isLogin()) {
			ArrayList<Account> accs = currentUser.getAccounts();
			if (accs.size() == 1) {
				accNum.setText(accs.get(0).getAccNumber());
				balance.setText(String.valueOf(accs.get(0).getBalance()));
				confirm.setText("Ok");
			}

		} else {
			displayAleart("Warning", "Internal Error");
		}
	}

	public void confirmButtonAction() {
		if (confirm.getText().equals("DISPLAY")) {
			doChanges();
		} else {
			changingVBox.getChildren().clear();
		}

	}

	public void ahiButtonAction(ActionEvent event) {
		if (currentUser != null && currentUser.isLogin()) {
			changeView("History.fxml", content);
		}
	}
	
	public void displayButtonAction() {
		if (displayButton.getText().equals("DISPLAY")) {
			Operations opps=new Operations();
			ObservableList<Model> modelData = opps.getTransactions(currentUser.getUname());
			from.setCellValueFactory(cellData -> cellData.getValue().from());
	        to.setCellValueFactory(cellData -> cellData.getValue().to());
	        date.setCellValueFactory(cellData -> cellData.getValue().date());
	        amount.setCellValueFactory(cellData -> cellData.getValue().amount());
	        transactionTable.setItems(modelData);
	        displayButton.setText("Cansel");
		} else {
			changingVBox4.getChildren().clear();
		}

	}

	public void contactBankButtonAction(ActionEvent event) {
		if (currentUser != null && currentUser.isLogin()) {
			changeView("SendMessage.fxml", content);
		}
	}

	public void sendButtonAction() {
		Operations opps = new Operations();
		int result = opps.insertMeassage(currentUser.getUname(), textMessage.getText(), dob.getValue().toString());
		if (result != -1) {
			displaySuccessAleart("Success", "Message Send Successfully");
			changingVBox.getChildren().clear();

		} else {
			displayAleart("Error", "Internal Error");
			changingVBox.getChildren().clear();
		}
	}

	public void transferMoneyButtonAction(ActionEvent event) {
		if (currentUser != null && currentUser.isLogin()) {
			changeView("MoneyTransfer.fxml", content);
		}
	}

	public void transferButtonAction(ActionEvent event) {
		if (currentUser.isLogin()) {
			Date currentDatetime = new Date(System.currentTimeMillis());
			java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
			Transaction trans = new Transaction(transAccNum.getText(), receAccNum.getText(), sqlDate,
					Double.parseDouble(transAmount.getText()));
			Operations opps = new Operations();
			User newUsr = opps.doTransaction(trans, currentUser);
			if (newUsr != null) {
				currentUser = newUsr;
				displaySuccessAleart("Success", "Your Transaction is success");
				changingVBox3.getChildren().clear();
			} else {
				displayAleart("Warning", "Trnasaction Unsuccessfull");
			}
		} else {
			displayAleart("Warning", "Internal Error");
		}
	}

	public void displayAleart(String headerText, String content) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText(headerText);
		alert.setContentText(content);
		alert.showAndWait();
	}

	public void displaySuccessAleart(String headerText, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setHeaderText(headerText);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public static void displayAleartStatic(String headerText, String content) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText(headerText);
		alert.setContentText(content);
		alert.showAndWait();
	}

}

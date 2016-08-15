package com.dlpds.ui;

import java.io.IOException;

import com.dlpds.bank.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterController {

	private User currentUser = null;

	@FXML
	private VBox regfxml;
	@FXML
	private TextField firstName;
	@FXML
	private TextField secondName;
	@FXML
	private TextField nic;
	@FXML
	private ChoiceBox gender;
	@FXML
	private DatePicker dob;
	@FXML
	private TextField address;
	@FXML
	private TextField phoneNum;
	@FXML
	private TextField mail;
	@FXML
	private TextField userName;
	@FXML
	private TextField pwd;
	@FXML
	private TextField pwdCon;
	@FXML
	private Button signUpButton;

	public RegisterController() {
		System.out.println("New Registration object created");
	}

	@FXML
	protected void initialization(User usr) {
		currentUser = usr;
	}

	public void changeWindow(String fxmlFile, Button button, String title) {
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

	@FXML
	public void signupButtonAction(ActionEvent event) {
		if(pwd.getText().equals(pwdCon.getText())){
			User newUser = new User(firstName.getText(), secondName.getText(), nic.getText(), gender.getValue().toString(),
					dob.getValue().toString(), address.getText(), phoneNum.getText(), mail.getText(), userName.getText(),
					pwd.getText());
			if(newUser.registerUser()){
				changeWindow("Login.fxml",signUpButton,"Login");
			}else{
				displayAleart("WARNING", "Internal Error");
			}
		}
		else{
			displayAleart("WARNING", "Password Mismatch");
		}
		
	}

	private void displayAleart(String headerText, String content) {
		Alert alert = new Alert(AlertType.WARNING);
		// alert.initOwner(mainApp.getPrimaryStage());
		alert.setTitle("Warning");
		alert.setHeaderText(headerText);
		alert.setContentText(content);

		alert.showAndWait();
	}

}

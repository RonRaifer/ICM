package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


import common.ClientConnector;
import common.MsgEnum;
import common.msgSendHandler;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LoginController implements Initializable{
	@FXML
    private Pane insidePane;
    @FXML
    private PasswordField tbPassowrd;
    @FXML
    private TextField tbLoginID;
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink hlForgot;
    @FXML
    private Label lblError;
    @FXML
    private Label lblStatus;
    
    ClientConnector client;
    
    @FXML 
	public void loginClick(ActionEvent event) {	
		if(tbLoginID.getText().isEmpty() || tbPassowrd.getText().isEmpty()) {
			lblError.setText("Username and Password can't be empty");
			lblError.setVisible(true);
		}else {
			ArrayList<Object> loginParams = new ArrayList<Object>();
			loginParams.add(tbLoginID.getText());
			loginParams.add(tbPassowrd.getText());
	    	msgSendHandler msg = new msgSendHandler(MsgEnum.LOGIN ,"Login", loginParams);
	    	client.handleMessageFromClientUI(msg);
		}
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	if (ConnectionController.getClient() != null && ConnectionController.getClient().isConnected()) {
    		client = ConnectionController.getClient();
    		lblStatus.setTextFill(Color.GREEN);
    		lblStatus.setText("Online");
    	}
    	else {
    		btnLogin.setDisable(true);
    		lblError.setText("Connect to server first");
    	}
	}
    
}

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.prism.paint.Color;

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
	public void loginClick(ActionEvent event) {	
		if(tbLoginID.getText().isEmpty() || tbPassowrd.getText().isEmpty()) {
			lblError.setText("Username and Password can't be empty");
			lblError.setVisible(true);
		}
    	
		
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		lblError.setVisible(false);
	}
    
}

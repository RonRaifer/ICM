package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import boundary.GuiManager;
import common.ClientConnector;
import common.Main;
import common.MsgEnum;
import common.ObjectManager;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	/**
	 * 
	 */
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
    
    private ClientConnector client;
    private static User userReceived;
    public static String errorMessage;
    @FXML 
	public void loginClick(ActionEvent event) throws InterruptedException, IOException {	
		if(tbLoginID.getText().isEmpty() || tbPassowrd.getText().isEmpty()) {
			lblError.setText("Username and Password can't be empty");
			lblError.setVisible(true);
		}else {
			User user = new User(tbLoginID.getText(), tbPassowrd.getText());
	    	ObjectManager msg = new ObjectManager(user, MsgEnum.LOGIN);
	    	client.handleMessageFromClientUI(msg);
	    	Thread.sleep(1500); //delay to get the message for server.
	    	if(userReceived != null) {
	    		GuiManager.guiLoader("Menu.fxml");
			}
			else{
				lblError.setText(errorMessage);
				lblError.setVisible(true);
			}
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
    public static void setUserReceived(User received) {
    	userReceived = received;
    }
    
}

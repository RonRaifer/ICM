package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import boundary.GuiManager;
import common.ClientConnector;
import common.Main;
import common.MsgEnum;
import common.ObjectManager;
import entity.User;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
	private AnchorPane apLoading;
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
    private Service<Void> loginBackgroud;
    private static User userReceived;
    public static String errorMessage;
    @FXML 
	public void loginClick(ActionEvent event) throws InterruptedException, IOException {
    	if(tbLoginID.getText().isEmpty() || tbPassowrd.getText().isEmpty()) {
			lblError.setText("Username and Password can't be empty");
			lblError.setVisible(true);
		}else {
			loginBackgroud = new Service<Void>() {
    		@Override
    		protected Task<Void> createTask() {
    			return new Task<Void>() {
    				@Override
    				protected Void call() throws Exception{
    					User user = new User(tbLoginID.getText(), tbPassowrd.getText());
    				    ObjectManager msg = new ObjectManager(user, MsgEnum.LOGIN);
    				    client.handleMessageFromClientUI(msg);
    				    Thread.sleep(2000);	
						return null;
    				}
    			};
    		}
    	};
	    	loginBackgroud.setOnRunning((e) -> apLoading.setVisible(true));
	    	loginBackgroud.setOnSucceeded((e) ->{
		    		if(userReceived != null) {
			    		Stage stage = (Stage) insidePane.getScene().getWindow();
						try {
							GuiManager.guiLoader("Menu.fxml", userReceived);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
			    		stage.close();
					}
					else{
						lblError.setText(errorMessage);
						lblError.setVisible(true);
						apLoading.setVisible(false);
					}
	    	});
	    	loginBackgroud.setOnFailed((e) ->{
	    		System.out.println("fail");
		});
	    	loginBackgroud.restart();
		}
    }
		
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	apLoading.setVisible(false);
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

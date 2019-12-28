package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import common.ClientConnector;
import common.ConnectorIF;
import common.ClientConnector.ConnectionDetails;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ConnectionController implements Initializable, ConnectorIF{
	public final static int DEFAULT_PORT = 5555;			//default port
	public final static String DEFAULT_HOST = "localhost";	//default host
	private static ClientConnector client;							//sever handler

	@FXML
	private Pane insidePane;

	@FXML
	private TextField tbHostName;

	@FXML
	private Button btnConnect;
	@FXML
	private Button btnDisconnect;

	@FXML
	private Label lblError;

	@FXML
	private TextField tbPort;

	@FXML
	private Label lblSuccess;
	@FXML
	private Label lblConnected;
	
	@FXML
	public void ConnectClick(ActionEvent event) {	//when click on 'connect'
		try {
			if(tbHostName.getText().isEmpty() || tbPort.getText().isEmpty()) {
				lblError.setText("HostName and Port can't be empty");
				lblError.setVisible(true);
			}else {
				ConnectionDetails.setServerDetails(tbHostName.getText(), Integer.parseInt(tbPort.getText()));
				this.client = new ClientConnector(this);
				connectionEstablished(true);
			}	
		} catch (IOException e) {
			lblError.setText("Connection Error");
			lblError.setVisible(true);
		}
	}
	@FXML
	public void DisconnectClick(ActionEvent event) {	//when click on 'disconnect'
		try {
    		client.closeConnection();
    		connectionEstablished(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static ClientConnector getClient() {		
		return client;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (ConnectionController.getClient() != null && ConnectionController.getClient().isConnected()) {
			connectionEstablished(true);
		}
		tbHostName.setText(ConnectionDetails.getHost());
		tbPort.setText(Integer.toString(ConnectionDetails.getPort()));
	}
	
	private void connectionEstablished(boolean isConnected) {
		lblSuccess.setVisible(isConnected);
		lblConnected.setVisible(isConnected);
		btnDisconnect.setVisible(isConnected);
		btnConnect.setDisable(isConnected);
		tbHostName.setDisable(isConnected);
		tbPort.setDisable(isConnected);
	}
	@Override
	public void display(String message) {
		// TODO Auto-generated method stub
		
	}
}

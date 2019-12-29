package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MenuController implements Initializable{
	@FXML
    private Button btnLogout;
    @FXML
    private Label lblName;
    @FXML
    private Label lblRole;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnMessages;
    @FXML
    private Button btnNewRequest;
    @FXML
    private Button btnMyRequests;
    @FXML
    private AnchorPane apCenterContent;
    private Button btnTemp;    
	private User user;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnTemp = new Button();
		sceneManager("HomePane", btnHome);
	}
	
	public void initData(User user) {
		lblName.setText(user.getFirstName() + " " + user.getLastName());
		lblRole.setText(user.getRole());
	}
	
	@FXML
    void homeClick(ActionEvent event) {
		sceneManager("HomePane", btnHome);
    }
	 
    @FXML
    void messagesClick(ActionEvent event) {
    	sceneManager("MessagesPane", btnMessages);
    }

    @FXML
    void myRequestsClick(ActionEvent event) {
    	sceneManager("MyRequestsPane", btnMyRequests);
    }

    @FXML
    void newRequestClick(ActionEvent event) {
    	sceneManager("NewRequestPane", btnNewRequest);
	}
	
	private void sceneManager(String fxmlName, Button button) {
		button.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 14px;" + "-fx-text-fill: DARKSLATEGRAY");
		clearButton();
		btnTemp = button;
		try 
        {
            AnchorPane newLoadedPane;  
            newLoadedPane =  FXMLLoader.load(getClass().getResource("/boundary/guifiles/" + fxmlName + ".fxml"));
            apCenterContent.getChildren().clear();
            apCenterContent.getChildren().add(newLoadedPane);
        }
        catch (IOException ex) {
            Logger.getLogger(EnterController.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	private void clearButton() {
		btnTemp.setStyle("-fx-font: normal bold 90 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: white;");
		btnTemp.setStyle("-fx-font-weight: regular;" + "-fx-font-size: 14px;" + "-fx-text-fill: BLACK");
	}
}

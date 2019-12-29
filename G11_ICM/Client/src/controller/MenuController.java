package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
	    @FXML
	    private Label lblPageName;
	    @FXML
	    private Label lblPageNavigationName;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}

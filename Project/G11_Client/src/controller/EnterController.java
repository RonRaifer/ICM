package controller;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController implements Initializable{
	@FXML private Pane InsidePane;
	@FXML private Pane apInside;
	@FXML private AnchorPane apMain;
	@FXML private Button btnLogin;
	
	@FXML 
	public void handleChangeView(ActionEvent event) {
		try 
        {
            Pane newLoadedPane;  
            newLoadedPane =  FXMLLoader.load(getClass().getResource("/boundary/guifiles/ServerConnection.fxml"));
            apInside.getChildren().clear();
            apInside.getChildren().add(newLoadedPane);
        }
        catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	@FXML 
	public void handleChangeView2(ActionEvent event) {
        System.out.print("LOGIN");
       
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub		
	}

	
}

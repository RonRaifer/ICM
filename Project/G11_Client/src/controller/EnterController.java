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

public class EnterController implements Initializable{
	@FXML private Pane InsidePane;
	@FXML private Pane apInside;
	@FXML private AnchorPane apMain;
	@FXML private Button btnLogin;
	
	
	@FXML 
	public void connectionPaneView(ActionEvent event) {
		try 
        {
            Pane newLoadedPane;  
            newLoadedPane =  FXMLLoader.load(getClass().getResource("/boundary/guifiles/ServerConnection.fxml"));
            apInside.getChildren().clear();
            apInside.getChildren().add(newLoadedPane);
        }
        catch (IOException ex) {
            Logger.getLogger(EnterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	@FXML 
	public void loginPaneView(ActionEvent event) {
		try 
        {
            Pane newLoadedPane;  
            newLoadedPane =  FXMLLoader.load(getClass().getResource("/boundary/guifiles/LoginPane.fxml"));
            apInside.getChildren().clear();
            apInside.getChildren().add(newLoadedPane);
        }
        catch (IOException ex) {
            Logger.getLogger(EnterController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try 
        {
            Pane newLoadedPane;  
            newLoadedPane =  FXMLLoader.load(getClass().getResource("/boundary/guifiles/LoginPane.fxml"));
            apInside.getChildren().clear();
            apInside.getChildren().add(newLoadedPane);
        }
        catch (IOException ex) {
            Logger.getLogger(EnterController.class.getName()).log(Level.SEVERE, null, ex);
        }		
	}

	
}

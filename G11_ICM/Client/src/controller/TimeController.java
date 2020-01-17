package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TimeController implements Initializable{

    @FXML
    private TextField tbTime;

    @FXML
    private Button btnRequest;

    @FXML
    private Label lblErr;

    
    private static ProcessesController d;
    

	public static void setCont(ProcessesController p) {
    	d=p;
    }
    
    
    @FXML
    void clickRequest(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

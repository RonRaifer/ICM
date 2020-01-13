package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class CheckingController implements Initializable{

    @FXML
    private TextArea tbFailure;

    @FXML
    private Button btnApprove;

    @FXML
    private Button btnReject;

    @FXML
    private Label lblStatus;

    @FXML
    void clickApprove(ActionEvent event) {

    }

    @FXML
    void clickReject(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

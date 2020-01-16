package controller;

import java.net.URL;
import java.util.ResourceBundle;

import common.ClientConnector;
import common.MsgEnum;
import common.ObjectManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
/**
 * 
 * the controller of the checking view
 *
 */
public class CheckingController implements Initializable {

    @FXML
    private TextArea tbFailure;

    @FXML
    private Button btnApprove;

    @FXML
    private Button btnFailure;

    @FXML
    private Label lblStatus;
    
    //attributes
    private ClientConnector client = ConnectionController.getClient();

    /**
     * this function is called when "approve" button is clicked, and cause the request
     * to move from Checking to Closing
     * @param event
     */
    @FXML
    void clickApprove(ActionEvent event) {
    	ObjectManager msg = new ObjectManager(ProcessesController.getSelectedID(), MsgEnum.APPROVE_CHECKING);
    	client.handleMessageFromClientUI(msg);
    	
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	lblStatus.setVisible(true);
    	lblStatus.setText("Your approval has been regisetered");
    	lblStatus.setTextFill(Color.GREEN);
    }

    /**
     * this function is called when "approve" button is clicked, and cause the request
     * to move from Checking to Execution
     * @param event
     */
    @FXML
    void clickFailure(ActionEvent event) {
    	
    	if(tbFailure.getText().isEmpty()) {
    		lblStatus.setVisible(true);
    		lblStatus.setText("Please enter your failure!");
    		lblStatus.setTextFill(Color.RED);
    		return;
    	}
    	System.out.println(ProcessesController.getSelectedID()+"*"+tbFailure.getText());
    	
    	String str = ProcessesController.getSelectedID()+"_"+tbFailure.getText();
    	String arr[] = str.split("_");
    	System.out.println(arr[0]+""+arr[1]);
    	ObjectManager msg = new ObjectManager(str, MsgEnum.REJECT_CHECKING);
    	client.handleMessageFromClientUI(msg);
    	
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	lblStatus.setVisible(true);
    	lblStatus.setText("Your failure has been regisetered");
    	lblStatus.setTextFill(Color.GREEN);
    	tbFailure.clear();

    }
/**
 * this function initialize the Checking view, and hide the Label
 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblStatus.setVisible(false);
		
	}

}

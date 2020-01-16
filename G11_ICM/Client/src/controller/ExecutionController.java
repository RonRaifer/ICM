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
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

public class ExecutionController implements Initializable {

    @FXML
    private TextArea tbInfo;

    @FXML
    private Button btnDone;
    
    @FXML
    private Label lblInfo;
    

    //attributes
    private ClientConnector client = ConnectionController.getClient();
    private static String info;
    private static ProcessesController d;
    public static void setCont(ProcessesController p) {
    	d=p;
    }
    @FXML
    void clickDone(ActionEvent event) {
    	ObjectManager msg = new ObjectManager(ProcessesController.getSelected().getIdrequest(),MsgEnum.EXECUTION_DONE);
    	client.handleMessageFromClientUI(msg);
    	
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	lblInfo.setVisible(true);
    	lblInfo.setText("Your approval has been regisetered");
    	lblInfo.setTextFill(Color.GREEN);
    	d.getCont().removeSelected(ProcessesController.getSelected()); //remove object from table
    	tbInfo.clear();
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		lblInfo.setVisible(false);
		ObjectManager msg = new ObjectManager(ProcessesController.getSelected().getIdrequest(), MsgEnum.GET_CHANGE_INFO);
		client.handleMessageFromClientUI(msg);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tbInfo.setText(info);
	}

	public static String getInfo() {
		return info;
	}

	public static void setInfo(String info) {
		ExecutionController.info = info;
	}
	
	

}

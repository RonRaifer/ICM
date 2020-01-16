package controller;


import java.net.URL;
import java.util.ResourceBundle;

import common.ClientConnector;
import common.MsgEnum;
import common.ObjectManager;
import entity.EvaluationReport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class ReviewController implements Initializable {

    @FXML
    private TextArea tbReport;

    @FXML
    private Button btnApprove;

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnReject;
    
    @FXML
    private Label lblStatus;


    //attributes
    private ClientConnector client = ConnectionController.getClient();
    private static EvaluationReport report;
    private static ProcessesController d;
    public static void setCont(ProcessesController p) {
    	d=p;
    }
    
    
    public static EvaluationReport getReport() {
		return report;
	}

	public static void setReport(EvaluationReport report) {
		ReviewController.report = report;
	}

	@FXML
    void clickApprove(ActionEvent event) {
		//need to send message to review leader
    }

	@FXML
    void clickReject(ActionEvent event) {
    	//need to send message to review leader
    }
	
    @FXML
    void clickInfo(ActionEvent event) {
    	ObjectManager msg = new ObjectManager(ProcessesController.getSelected().getIdrequest(), MsgEnum.MORE_INFO_REVIEW);
    	client.handleMessageFromClientUI(msg);
    	
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	lblStatus.setVisible(true);
    	lblStatus.setTextFill(Color.GREEN);
    	lblStatus.setText("Request is now in Evaluation stage again");
    	d.getCont().removeSelected(ProcessesController.getSelected()); //remove object from table
    	tbReport.clear();
    	
    }

    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		lblStatus.setVisible(false);
		ObjectManager msg = new ObjectManager(ProcessesController.getSelected().getIdrequest(), MsgEnum.GET_EV_REPORT);
		client.handleMessageFromClientUI(msg);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		String str = "Location of change: "+report.getLocation()+"\n\n"+
					"Description: "+report.getDescription()+"\n\n"+
					"Result: "+report.getResult()+"\n\n"+
					"Risk: "+report.getRisk()+"\n\n"+
					"Time evaluation: "+report.getTime();
		
		tbReport.setText(str);
		
		
	}
    
    

}

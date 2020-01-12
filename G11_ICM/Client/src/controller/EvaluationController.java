package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import boundary.GuiManager;
import common.ClientConnector;
import common.MsgEnum;
import common.ObjectManager;
import entity.EvaluationReport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class EvaluationController implements Initializable{

    @FXML
    private Label lblErr;

    @FXML
    private TextArea tbTime;

    @FXML
    private TextArea tbRisk;

    @FXML
    private TextArea tbResult;

    @FXML
    private TextArea tbDescription;

    @FXML
    private TextField tbLocation;

    @FXML
    private Button btnEvalue;

    @FXML
    private Hyperlink hyperReqInfo;

    //attributes
    private ClientConnector client = ConnectionController.getClient();
    private static boolean previousReport;
    private static EvaluationReport report;
	
    
    
    public static EvaluationReport getReport() {
		return report;
	}

	public static void setReport(EvaluationReport report) {
		EvaluationController.report = report;
	}

	public static boolean isPreviousReport() {
		return previousReport;
	}

	public static void setPreviousReport(boolean previousReport) {
		EvaluationController.previousReport = previousReport;
	}

    @FXML
    void clickEvaluate(ActionEvent event) {
    	
    	if(checkInput()) {
    		lblErr.setVisible(true);
    		lblErr.setTextFill(Color.RED);
    		lblErr.setText("One filed or more are empty!");
    		return;
    	}
    	
    	EvaluationReport temp = new EvaluationReport();
    	temp.setDescription(tbDescription.getText());
    	temp.setLocation(tbLocation.getText());
    	temp.setResult(tbResult.getText());
    	temp.setRisk(tbRisk.getText());
    	temp.setTime(tbTime.getText());
    	temp.setIdReq(ProcessesController.getSelectedID());
    	
    	ObjectManager msg = new ObjectManager(temp, MsgEnum.ADD_EV_REPORT);
    	client.handleMessageFromClientUI(msg);
    	
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	lblErr.setVisible(true);
    	lblErr.setTextFill(Color.GREEN);
    	lblErr.setText("Evaluation report added successfully!");
    	clearAll();

    }

    @FXML
    void clickInfo(ActionEvent event) throws IOException { //raise popup with selected request details
    	GuiManager.popUpLoader("RequestView", ProcessesController.getSelectedID());
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		lblErr.setVisible(false);
		
		
		//checking if there is previous evaluation report
		ObjectManager msg = new ObjectManager(ProcessesController.getSelectedID(), MsgEnum.CHECK_PRE_EV);
		client.handleMessageFromClientUI(msg);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(report!=null) {
			tbDescription.setText(report.getDescription());
			tbLocation.setText(report.getLocation());
			tbResult.setText(report.getResult());
			tbRisk.setText(report.getRisk());
			tbTime.setText(report.getTime());
			return;
		}
		
		
	}
	
	public void clearAll() {
		tbDescription.clear();
		tbLocation.clear();
		tbResult.clear();
		tbRisk.clear();
		tbTime.clear();
	}
	
	public boolean checkInput() {
		if(tbDescription.getText().isEmpty())
			return true;
		
		if(tbLocation.getText().isEmpty())
			return true;
		
		if(tbResult.getText().isEmpty())
			return true;
		
		if(tbResult.getText().isEmpty())
			return true;
		
		if(tbTime.getText().isEmpty())
			return true;
		
		return false;
	}

}

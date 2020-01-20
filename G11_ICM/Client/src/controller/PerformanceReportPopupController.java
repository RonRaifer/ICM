package controller;

import java.net.URL;
import java.util.ResourceBundle;

import common.MsgEnum;
import common.ObjectManager;
import entity.PerformanceReport;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class PerformanceReportPopupController implements Initializable{
	@FXML
    private Label durationApprovedExtensions;
	@FXML
	private Label durationActivityTimeAdded;
	
	private static PerformanceReport perRep=null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObjectManager generatePerformanceReport =new ObjectManager(perRep, MsgEnum.CREATE_PERFORMANCE_REPORT);
		ConnectionController.getClient().handleMessageFromClientUI(generatePerformanceReport);
		durationApprovedExtensions.setText(perRep.getDurationApprovedExtensions());
		durationActivityTimeAdded.setText(perRep.getDurationActivityTimeAdded());
	}

	public static void setPerRep(PerformanceReport perRep) {
		PerformanceReportPopupController.perRep = perRep;
	}
}

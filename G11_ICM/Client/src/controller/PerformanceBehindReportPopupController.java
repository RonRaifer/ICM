package controller;

import java.net.URL;
import java.util.ResourceBundle;

import entity.PerformanceBehindReport;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PerformanceBehindReportPopupController implements Initializable {
	@FXML
    private Label lblMedian;

    @FXML
    private Label lblstandarddeviation;
    
    @FXML
    private ComboBox<String> cmbSystem;
    
    @FXML
    private TableView<String> tblFrequencyDistribution;
    
    @FXML
    private TableColumn<String, String> colRange;

    @FXML
    private TableColumn<String, Integer> colTotalnRange;
    
    private static PerformanceBehindReport perBehindRep=null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	public static void setPerBehindRep(PerformanceBehindReport perBehindRep) {
		PerformanceBehindReportPopupController.perBehindRep = perBehindRep;
	}
}

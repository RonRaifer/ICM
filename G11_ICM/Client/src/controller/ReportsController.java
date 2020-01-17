package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entity.ActivityReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ReportsController implements Initializable {
	@FXML
    private Label lblPageName;

    @FXML
    private Label lblPageNavigationName;

    @FXML
    private ComboBox<String> cmbReports;

    @FXML
    private Button btnGenerateReport;

    @FXML
    private AnchorPane duration;
    @FXML
    private TableView<ActivityReport> tblActivityReports;

    @FXML
    private TableColumn<ActivityReport, String> colStartDate;

    @FXML
    private TableColumn<ActivityReport, String> colEndDate;

    @FXML
    void generateNewReport(ActionEvent event) {
    	if(cmbReports.getSelectionModel().isEmpty()) {
    		//label nothing chose.
    	}else {
    		if(cmbReports.getSelectionModel().getSelectedItem().equals("Activity report")) {
    			//do
    		}
    		if(cmbReports.getSelectionModel().getSelectedItem().equals("Performance report")) {
    			//do
    		}
    	}
    }
    @FXML
    void onSelectedItem(ActionEvent event) {
    	if(cmbReports.getSelectionModel().getSelectedItem().equals("Activity report")) {
    		duration.setVisible(true);
    	}else {
    		duration.setVisible(false);
    	}
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		duration.setVisible(false);
		List<String> reportsList= new ArrayList<String>();
		reportsList.add("Activity report");
		reportsList.add("Performance report");
		reportsList.add("Performance behind report");
		ObservableList<String> obList=FXCollections.observableList(reportsList);
		cmbReports.getItems().clear();
		cmbReports.setItems(obList);
	}
}

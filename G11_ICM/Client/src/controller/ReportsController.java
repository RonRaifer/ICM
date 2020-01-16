package controller;

import entity.ActivityReport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ReportsController {
	@FXML
    private Label lblPageName;

    @FXML
    private Label lblPageNavigationName;

    @FXML
    private ComboBox<String> cmbReports;

    @FXML
    private Button btnGenerateReport;

    @FXML
    private AnchorPane Duration;
    @FXML
    private TableView<ActivityReport> tblActivityReports;

    @FXML
    private TableColumn<ActivityReport, String> colStartDate;

    @FXML
    private TableColumn<ActivityReport, String> colEndDate;

    @FXML
    void generateNewReport(ActionEvent event) {

    }
}

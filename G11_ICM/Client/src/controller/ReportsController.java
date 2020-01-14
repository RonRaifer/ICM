package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ReportsController {
	@FXML
    private Label lblPageName;

    @FXML
    private Label lblPageNavigationName;

    @FXML
    private ComboBox<?> cmbReports;

    @FXML
    private Button btnGenerateReport;

    @FXML
    private AnchorPane Duration;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    void generateNewReport(ActionEvent event) {

    }
}

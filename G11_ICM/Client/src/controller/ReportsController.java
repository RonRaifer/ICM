package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    void generateNewReport(ActionEvent event) {

    }
}

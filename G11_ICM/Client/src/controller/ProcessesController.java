package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.ClientConnector;
import common.MsgEnum;
import common.ObjectManager;
import entity.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ProcessesController implements Initializable {

    @FXML
    private Label lblPageName;

    @FXML
    private Label lblPageNavigationName;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabAll;

    @FXML
    private TableView<?> tblAll;

    @FXML
    private Tab tabEvaluation;

    @FXML
    private TableView<Request> tblEvaluation;

    @FXML
    private TableColumn<Request, String> colID;

    @FXML
    private TableColumn<Request, String> colState;

    @FXML
    private TableColumn<Request, String> colRequest;

    @FXML
    private TableColumn<Request, String> colPurpose;

    @FXML
    private TableColumn<Request, String> colComment;

    @FXML
    private Button btnEvalue;

    @FXML
    private TextField tbLocation;

    @FXML
    private TextArea tbDescription;

    @FXML
    private TextArea tbResult;

    @FXML
    private TextArea tbRisk;

    @FXML
    private TextArea tbTime;

    @FXML
    private Label lblErr;

    @FXML
    private Tab tabExamine;

    @FXML
    private Tab tabExecute;

    @FXML
    private Tab tabClosing;

    @FXML
    private Tab tabClose;
    
    
  //attribute
    private static ArrayList<Request> evaluationList;
    private ClientConnector client = ConnectionController.getClient();
    private static ObservableList<Request> List = FXCollections.observableArrayList();
    
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		lblErr.setVisible(false);
		
		
		
	}
    
    
    
    
    
    
    

    
    
    
}

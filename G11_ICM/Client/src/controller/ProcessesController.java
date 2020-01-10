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
import javafx.event.ActionEvent;
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
import javafx.scene.control.cell.PropertyValueFactory;

public class ProcessesController implements Initializable{

    @FXML
    private Label lblPageName;

    @FXML
    private Label lblPageNavigationName;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabAll;

    @FXML
    private TableView<Request> tblAll;

    @FXML
    private TableColumn<Request, String> colID1;

    @FXML
    private TableColumn<Request, String> colDate1;

    @FXML
    private TableColumn<Request, String> colStage1;

    @FXML
    private Tab tabEvaluation;

    @FXML
    private TableView<?> tblEvaluation;

    @FXML
    private TableColumn<?, ?> colID2;

    @FXML
    private TableColumn<?, ?> colState;

    @FXML
    private TableColumn<?, ?> colRequest;

    @FXML
    private TableColumn<?, ?> colPurpose;

    @FXML
    private TableColumn<?, ?> colComment;

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
    private TableColumn<?, ?> colID3;

    @FXML
    private Tab tabExecute;

    @FXML
    private Tab tabClosing;

    @FXML
    private Tab tabClose;
    
    
    //attribute
  //attributes
    private static ArrayList<Request> tbl1;
    private static ArrayList<Request> tbl2;
    private ClientConnector client = ConnectionController.getClient();
    private static ObservableList<Request> List = FXCollections.observableArrayList();

    @FXML
    void clickAll(ActionEvent event) {
    	
    	String id = LoginController.getLoggedUser().getIdUser();
    	ObjectManager obm = new ObjectManager(id, MsgEnum.PRO_ALL_TBL);
    	client.handleMessageFromClientUI(obm);
    	
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	colID1.setCellValueFactory(new PropertyValueFactory<>("idReq"));
		colDate1.setCellValueFactory(new PropertyValueFactory<>("stageDueDate"));
		colStage1.setCellValueFactory(new PropertyValueFactory<>("currentStage"));
		
		List = FXCollections.observableArrayList(tbl1);
		tblAll.setItems(List);
		
    	
    	
    }

    @FXML
    void clickChecking(ActionEvent event) {

    }

    @FXML
    void clickEva(ActionEvent event) {

    }

    @FXML
    void clickExecute(ActionEvent event) {

    }

    @FXML
    void clickReview(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		//need to check if if is inspector, if it is not - disable closing tab
		//can also hide unessecery buttons in other tabs here
		
		
		
		
		//end of checking what user it is
		
		
		//calling clickAll function
		clickAll(null);
		
		
	}

	public static ArrayList<Request> getTbl1() {
		return tbl1;
	}

	public static void setTbl1(ArrayList<Request> tbl1) {
		ProcessesController.tbl1 = tbl1;
	}

	public static ArrayList<Request> getTbl2() {
		return tbl2;
	}

	public static void setTbl2(ArrayList<Request> tbl2) {
		ProcessesController.tbl2 = tbl2;
	}
    
    

}

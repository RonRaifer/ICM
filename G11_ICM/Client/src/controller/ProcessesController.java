package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.ClientConnector;
import common.MsgEnum;
import common.ObjectManager;
import entity.Request;
import entity.RequestHandling;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private Tab tabTimeDetermine;

    @FXML
    private TableView<RequestHandling> tblTimeDetermine;

    @FXML
    private TableColumn<RequestHandling, String> col_requestId;

    @FXML
    private TableColumn<RequestHandling, String> col_stage;

    @FXML
    private TableColumn<RequestHandling, String> col_status;
    //delete later
    @FXML
    private TableView<RequestHandling> tblTimeDetermine1;

    @FXML
    private TableColumn<RequestHandling, String> col_requestId1;

    @FXML
    private TableColumn<RequestHandling, String> col_stage1;

    @FXML
    private TableColumn<RequestHandling, String> col_status1;

   // @FXML
  //  private TextArea tbTime;

    @FXML
    private Hyperlink hlShowDetails;

    @FXML
    private Button btnTimeRequest;

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
    private static ArrayList<RequestHandling> arralistOfProcesses = null;
    private static ArrayList<RequestHandling> arralistOfTimeRequests = null;
	private ObservableList<RequestHandling> List = null;
	
	public static void setListOfProcesses(ArrayList<RequestHandling> array) {
		arralistOfProcesses = new ArrayList<>(array);
    }
	public static void setListOfTimeRequests(ArrayList<RequestHandling> array) {
		arralistOfTimeRequests = new ArrayList<>(array);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblErr.setVisible(false);
		

	}
	@FXML
    void event(Event ev) {
       if (tabAll.isSelected()) {
        	ObjectManager viewProcesses = new ObjectManager(arralistOfProcesses, MsgEnum.VIEW_PROCESSES);
    		ConnectionController.getClient().handleMessageFromClientUI(viewProcesses);
    		

        	try {
    			Thread.sleep(1000);
    			//if(arralistOfProcesses.isEmpty()) tabPane.getTabs().remove(tabAll); //for later

    			
    			col_requestId1.setCellValueFactory(new PropertyValueFactory<>("idrequest"));
    			col_stage1.setCellValueFactory(new PropertyValueFactory<>("currentStage"));
    			col_status1.setCellValueFactory(new PropertyValueFactory<>("status"));
    			List = FXCollections.observableArrayList(arralistOfProcesses);
    			tblTimeDetermine1.setItems(List);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }

    } 
	@FXML
	void event2(Event ev) {
        if (tabTimeDetermine.isSelected()) {
        	ObjectManager timeRequests = new ObjectManager(arralistOfProcesses, MsgEnum.VIEW_PROCESSES_TO_BE_DETERMINED);
    		ConnectionController.getClient().handleMessageFromClientUI(timeRequests);
    		

        	try {
    			Thread.sleep(1000);
    			//if(arralistOfProcesses.isEmpty()) tabPane.getTabs().remove(tabAll); //for later

    			
    			col_requestId.setCellValueFactory(new PropertyValueFactory<>("idrequest"));
    			col_stage.setCellValueFactory(new PropertyValueFactory<>("currentStage"));
    			col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
    			List = FXCollections.observableArrayList(arralistOfTimeRequests);
    			tblTimeDetermine.setItems(List);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
    } 
    
}

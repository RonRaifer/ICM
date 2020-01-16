package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import boundary.GuiManager;
import common.ClientConnector;
import common.MsgEnum;
import common.ObjectManager;
import entity.ActionsNeeded;
import entity.Messages;
import entity.RequestHandling;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ProcessInspectorController implements Initializable{
	 @FXML
	 private TabPane tabPane;

	 @FXML
	 private Tab tabWaitingActions;

	 @FXML
	 private TableView<ActionsNeeded> tblActionsNeeded;

	 @FXML
	 private TableColumn<ActionsNeeded, String> col_requestId;

	 @FXML
	 private TableColumn<ActionsNeeded, String> col_chargeId;

	 @FXML
	 private TableColumn<ActionsNeeded, String> col_stage;

	 @FXML
	 private TableColumn<ActionsNeeded, String> col_actions;

	 @FXML
	 private Label lblPickMsg;

	 @FXML
	 private AnchorPane apAppoint;
	 @FXML
	 private AnchorPane apAppointExecutor;

	 @FXML
	 private ComboBox<String> cmbEmployees;

	 @FXML
	 private Label lblSubTitle;

	 @FXML
	 private Button btnAppoint;
	 @FXML
	 private Button btnAppointExecutor;
	 @FXML
	 private Label lblId;
	 @FXML
	 private Hyperlink hlReplace;
	 @FXML
	 private AnchorPane apPick;
	 @FXML
	 private Label lblError;
	 @FXML
	 private Label lblGreat;
	 @FXML
	 private Pane pError;
	 
	 private ClientConnector client = ConnectionController.getClient();
	 private static ArrayList<ActionsNeeded> arralistOfActions = null;
	 private static ArrayList<User> arralistOfEmployees = null;
	 private static ObservableList<ActionsNeeded> List = FXCollections.observableArrayList();
	 private boolean empListView = false;
	 private ActionsNeeded action;
		
	public static void setListOfActions(ArrayList<ActionsNeeded> array) {
		arralistOfActions = new ArrayList<>(array);
	}
	public static void setListOfEmployees(ArrayList<User> array) { //Set the list of employees received from server
		arralistOfEmployees = new ArrayList<>(array);
    }
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblGreat.setVisible(false);
		clearScreen();
		ObjectManager viewActions = new ObjectManager(arralistOfActions, MsgEnum.VIEW_ACTIONS);
		ConnectionController.getClient().handleMessageFromClientUI(viewActions);
		col_requestId.setCellValueFactory(new PropertyValueFactory<>("idrequest"));
		col_chargeId.setCellValueFactory(new PropertyValueFactory<>("idCharge"));
		col_stage.setCellValueFactory(new PropertyValueFactory<>("stage"));
		col_actions.setCellValueFactory(new PropertyValueFactory<>("actionsNeeded"));
	    try {
			Thread.sleep(1000);
			if(arralistOfActions.isEmpty()) lblGreat.setVisible(true);
			else {
				List = FXCollections.observableArrayList(arralistOfActions);
				tblActionsNeeded.setItems(List);
			}		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}  
	@FXML
    private void onItemClick(MouseEvent event) {
		action = tblActionsNeeded.getSelectionModel().getSelectedItem();
		String selected = action.getActionsNeeded();
		clearScreen();
		if(selected.equals("Evaluator Appointment")) { //if needs to appoint Evaluator
			if(action.getIdCharge().equals("None")) { //if no automatically Evaluator appointed
				lblSubTitle.setText("System Charge Does Not Exist, Appoint Evaluator Below:");
				lblId.setVisible(false);
				hlReplace.setVisible(false);
				fillCmbEmployees();
			}
			else {
				lblSubTitle.setText("System has automaticly appointed Evaluator ID:");
				lblId.setVisible(true);
				hlReplace.setVisible(true);
				lblId.setText(action.getIdCharge());
			}
			apAppoint.setVisible(true);
		}
		if(selected.equals("Executor Appointment")) { //if needs to appoint Executor
			fillCmbEmployees();
			apAppointExecutor.setVisible(true);
		}
		lblPickMsg.setVisible(false);
	}
	@FXML
    private void onChangeAutoEvaluatorClick(ActionEvent event) { //When click on replace the automatic appointed evaluator
		fillCmbEmployees();
		lblId.setVisible(false);
		hlReplace.setVisible(false);
		lblSubTitle.setText("Pick new Evaluator from the list below:");
	}
	/**
	 * When employee was chosen from employee combobox
	 */
	@FXML
    private void onApproveExecutorClick(ActionEvent event) { 
		String empSelectedID = "";
		if(cmbEmployees.getSelectionModel().isEmpty()) {
			GuiManager.showError(lblError, pError, "You must pick an employee first");
		}
		else {
			empSelectedID = arralistOfEmployees.get(cmbEmployees.getSelectionModel().getSelectedIndex()).getIdUser();
			ObjectManager msg = new ObjectManager(empSelectedID, action, MsgEnum.APPOINT_STAGE_CHARGE);
	    	client.handleMessageFromClientUI(msg);

	    	try {
				Thread.sleep(500);
				tblActionsNeeded.getItems().remove(action); //remove the line from table.
				GuiManager.showSuccess(lblError, pError, "Employee: " + empSelectedID +" Is Now Executor"); //show success message
				clearScreen();
				lblPickMsg.setVisible(true);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	@FXML
    private void onApproveEvaluatorClick(ActionEvent event) { 
		String empSelectedID = "";
		if(empListView) {
			if(cmbEmployees.getSelectionModel().isEmpty()) {
				GuiManager.showError(lblError, pError, "You must pick an employee first"); //show error
			}
			else {
				empSelectedID = arralistOfEmployees.get(cmbEmployees.getSelectionModel().getSelectedIndex()).getIdUser();
			}
		}else {
			empSelectedID = action.getIdCharge();
		}
		if(empSelectedID != "") { //if employee selected
			 //send new evaluator id to server
			ObjectManager msg = new ObjectManager(empSelectedID, action, MsgEnum.APPOINT_STAGE_CHARGE);
	    	client.handleMessageFromClientUI(msg);

	    	try {
				Thread.sleep(500);
				tblActionsNeeded.getItems().remove(action); //remove the line from table.
				GuiManager.showSuccess(lblError, pError, "Employee: " + empSelectedID +" Is Now Evaluator"); //show success message
				clearScreen();
				lblPickMsg.setVisible(true);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	/**
	 * Fills the Employees combobox with workers
	 */
	private void fillCmbEmployees() {
		empListView = true;
		ObjectManager viewEmployees = new ObjectManager(arralistOfEmployees, MsgEnum.VIEW_EMPLOYEES_TO_APPOINT);
    	ConnectionController.getClient().handleMessageFromClientUI(viewEmployees);
    	ObservableList<String> empList;
    	List<String> emp = new ArrayList<String>();

    	try {
			Thread.sleep(1500);
			for(User u : arralistOfEmployees) {
				emp.add(u.getFirstName() +" "+ u.getLastName());
	    	}
			empList = FXCollections.observableList(emp);
			cmbEmployees.getItems().clear();
			cmbEmployees.setItems(empList);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	apPick.setVisible(true);
	}
	/**
	 * Hide irrelevant AnchorPanes and Labels
	 */
	private void clearScreen() {
		empListView = false;
		apPick.setVisible(false);
		apAppoint.setVisible(false);
		apAppointExecutor.setVisible(false);
	}
}

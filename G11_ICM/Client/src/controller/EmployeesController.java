package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundary.GuiManager;
import common.ClientConnector;
import common.MsgEnum;
import common.ObjectManager;
import entity.Employee;
import entity.Messages;
import entity.User;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class EmployeesController implements Initializable{
	/**
	 * Tables and Objects for department attach view
	 */
	@FXML
	private TableView<User> tblEmployees;
	@FXML
	private TableView<User> tblToAdd;
	@FXML
	private TableColumn<User, String> col_id;
	@FXML
	private TableColumn<User, String> col_idToAdd;
	@FXML
	private TableColumn<User, String> col_nameToAdd;
	@FXML
	private TableColumn<User, String> col_name;
	@FXML
	private TableColumn<User, String> col_role;
	@FXML
	private TableColumn<User, String> col_lastName;
	@FXML
	private TableColumn<User, String> col_lastNameToAdd;
	@FXML
	private TableColumn<User, User> col_optionsToAdd;
	@FXML
	private TableColumn<User, User> col_options;
	@FXML
	private Tab tabPermissions;
	@FXML
	private Tab tabAttach;
	@FXML
	private Pane pMsg;
	@FXML
	private Label lblMsg;
	/**
	 * Tables and Objects for permissions view
	 */
	@FXML
	private TableView<User> tblEmployeesWithRoles;
	@FXML
	private TableColumn<User, String> col_empID;
	@FXML
	private TableColumn<User, String> col_empFirstName;
	@FXML
	private TableColumn<User, String> col_empLastName;
	@FXML
	private TableColumn<User, String> col_empEmail;
	@FXML
	private TableColumn<User, String> col_empRole;

	@FXML
	private AnchorPane apLoading;
	@FXML
    private TitledPane paneInSide;
	@FXML
    private TitledPane paneOutSide;
	@FXML
    private TitledPane panePermission;
	
	public static ArrayList<User> arralistOfEmployees = new ArrayList<User>();
	private ArrayList<User> inDepartment;
	private ArrayList<User> outSideDepartment;
	private ObservableList<User> List = null;
	
	public static void setListOfEmployees(ArrayList<User> array) {
		arralistOfEmployees = new ArrayList<>(array);
    }
	
	@FXML
	private void onTabAttachSelect(Event ev) {
		if (tabAttach.isSelected()) {
			paneOutSide.setVisible(false);
			paneInSide.setVisible(false);
			arralistOfEmployees.clear();
			inDepartment = new ArrayList<User>();
	   		outSideDepartment = new ArrayList<User>();   		
	    	//Task to get data from DB
	   		Task<ArrayList<User>> task = new Task<ArrayList<User>>() {
	   			@Override
	   			public ArrayList<User> call() throws InterruptedException {
	   				ObjectManager userWithRoles = new ObjectManager(arralistOfEmployees, MsgEnum.VIEW_EMPLOYEES); //updating departments for employee changed
	   				ConnectionController.getClient().handleMessageFromClientUI(userWithRoles);
	   				while(arralistOfEmployees.isEmpty())
	   					Thread.sleep(100);
	   				return arralistOfEmployees;
	   			}
	   		};
	   		task.setOnSucceeded(e -> {
	    	    	ArrayList<User> result = task.getValue();
	    	    	apLoading.setVisible(false);
	    	    	col_id.setCellValueFactory(new PropertyValueFactory<>("idUser"));
	    	   		col_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
	    	   		col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName")); 	//type of employee. Worker/Information Engineer/Manager
	    	   		col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
	    	   		
	    	   		col_idToAdd.setCellValueFactory(new PropertyValueFactory<>("idUser"));
	    	   		col_nameToAdd.setCellValueFactory(new PropertyValueFactory<>("firstName"));
	    	   		col_lastNameToAdd.setCellValueFactory(new PropertyValueFactory<>("lastName"));
	    	   		col_options.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
	    	   		col_options.setCellFactory(param -> new TableCell<User, User>() { 
	    	   			@FXML
	    	   		    private final Button btn = new Button("Remove From Department");

	    	   		    protected void updateItem(User user, boolean empty) {
	    	   		        super.updateItem(user, empty);

	    	   		        if (user == null) {
	    	   		            setGraphic(null);
	    	   		            return;
	    	   		        }

	    	   		        setGraphic(btn);
	    	   		        btn.setOnAction(
	    	   		            event -> {
	    	   		            	String role = user.getRole();
	    	   		            	if(role.equals("Inspector") || role.equals("Manager") || role.equals("Review Leader") || role.equals("Review Member")) {
	    	   		            		Alert alert = new Alert(AlertType.INFORMATION);
	    	   		                	alert.setTitle("Unable to remove "+ role);
	    	   		                	alert.setHeaderText("You are trying to remove "+role + " Employee");
	    	   		                	alert.setContentText("First, remove the role of this employee by choosing the Permission tab, and then you will be able to remove him.");
	    	   		                	alert.showAndWait();
	    	   		            	}else {
	    	   		            		tblToAdd.getItems().add(user);
	    	   			            	getTableView().getItems().remove(user);
	    	   		            	}
	    	   		            }
	    	   		        );
	    	   		    }
	    	   		});
	    	   		
	    	   		col_optionsToAdd.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
	    	   		col_optionsToAdd.setCellFactory(param -> new TableCell<User, User>() { 
	    	   			@FXML
	    	   		    private final Button btn = new Button("Add To Department");

	    	   		    protected void updateItem(User user, boolean empty) {
	    	   		        super.updateItem(user, empty);

	    	   		        if (user == null) {
	    	   		            setGraphic(null);
	    	   		            return;
	    	   		        }
	    	   		        setGraphic(btn);
	    	   		        btn.setOnAction(
	    	   		            event -> {
	    	   		            	tblEmployees.getItems().add(user);
	    	   		            	getTableView().getItems().remove(user);
	    	   		            }
	    	   		        );
	    	   		    }
	    	   		});    	   
	    	    	for(User u : result) {
		   				String dep = u.getDepartment();
		   				if(dep.equals("Information Technology")) 
		   					inDepartment.add(u);
		   				else outSideDepartment.add(u);
		   	    	}
		   			List = FXCollections.observableArrayList(inDepartment);
		   			tblEmployees.setItems(List);
		   			List = FXCollections.observableArrayList(outSideDepartment);
		   			tblToAdd.setItems(List);
		   			paneInSide.setVisible(true);
	    	    	paneOutSide.setVisible(true);
	    	    });
	    	    task.setOnFailed(event -> {
	    	    	paneInSide.setVisible(false);
	    	    	paneOutSide.setVisible(false);
	    	    });     
	    	    task.setOnRunning(event -> {
	    	    	apLoading.setVisible(true);
	    	    }); 
	    	    new Thread(task).start();
	    }
	}
	@FXML
    private void onTabPermissionsSelect(Event ev) {
       if (tabPermissions.isSelected()) {
    	   panePermission.setVisible(false);
    	   arralistOfEmployees.clear();
    	   col_empID.setCellValueFactory(new PropertyValueFactory<>("idUser"));
    	   col_empFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    	   col_empLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    	   col_empEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    	   col_empRole.setCellValueFactory(new PropertyValueFactory<>("role"));
    	   
    	   //Task to get data from DB
    	   Task<ArrayList<User>> task = new Task<ArrayList<User>>() {
    	        @Override
    	        public ArrayList<User> call() throws InterruptedException {
    	        	ObjectManager userWithRoles = new ObjectManager(arralistOfEmployees, MsgEnum.VIEW_EMPLOYEES_WITH_ROLES); //updating departments for employee changed
    	       	   	ConnectionController.getClient().handleMessageFromClientUI(userWithRoles);
    	       	   	while(arralistOfEmployees.isEmpty())
    	       	   		Thread.sleep(100);
    	       	   	return arralistOfEmployees;
    	        }
    	    };
    	    task.setOnSucceeded(e -> {
    	    	ArrayList<User> result = task.getValue();
    	    	apLoading.setVisible(false);
    	    	List = FXCollections.observableArrayList(result);
    	    	tblEmployeesWithRoles.setItems(List);
    	    	panePermission.setVisible(true);
    	    });
    	    task.setOnFailed(event -> {
    	    	panePermission.setVisible(false);
    	    });     
    	    task.setOnRunning(event -> {
    	    	apLoading.setVisible(true);
    	    }); 
    	    new Thread(task).start();
       	}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		arralistOfEmployees.clear();
		clearScreen();
	}
	/**
	 * Updates the department employees with changes.
	 * @param event When click on button
	 */
	@FXML
    private void onUpdateDepartmentClick(ActionEvent event) { 
		ArrayList<User> changed = new ArrayList<User>(); //contains all changes in department to perform
		//users inside department
		for(User u : inDepartment) {
			if(tblToAdd.getItems().contains(u)) { //check if employee removed from department
				u.setDepartment("");		//update this user department
				changed.add(u);
			}
		}
		//users outside department
		for(User u : outSideDepartment) {
			if(tblEmployees.getItems().contains(u)) { //check if employee added to department
				u.setDepartment("Information Technology");		//update this user department
				changed.add(u);
			}
		}
		if(changed.isEmpty()) { //if nothing changed
			GuiManager.showError(lblMsg, pMsg, "No Changes Detected");
		}else {
			ObjectManager updateDepartment = new ObjectManager(changed, MsgEnum.UPDADE_DEPARTMENT); //updating departments for employee changed
			ConnectionController.getClient().handleMessageFromClientUI(updateDepartment);
			GuiManager.showSuccess(lblMsg, pMsg, "Department Updated Successfuly!");
			
			for(User u : changed) {
				if(inDepartment.contains(u))inDepartment.remove(u);
				else inDepartment.add(u);
				if(outSideDepartment.contains(u)) outSideDepartment.remove(u);
				else outSideDepartment.add(u);
			}
		}
	}
	/**
	 * Hide all before stage starts
	 */
	private void clearScreen() {
		apLoading.setVisible(false);
		pMsg.setVisible(false);
		panePermission.setVisible(false);
		paneOutSide.setVisible(false);
		paneInSide.setVisible(false);
	}
}

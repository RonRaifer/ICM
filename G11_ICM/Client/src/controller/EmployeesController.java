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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EmployeesController implements Initializable{
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
	private Pane pMsg;
	@FXML
	private Label lblMsg;
	
	public static ArrayList<User> arralistOfEmployees = null;
	private ArrayList<User> inDepartment;
	private ArrayList<User> outSideDepartment;
	private ObservableList<User> List = null;
	
	public static void setListOfEmployees(ArrayList<User> array) {
		arralistOfEmployees = new ArrayList<>(array);
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pMsg.setVisible(false);
		inDepartment = new ArrayList<User>();
		outSideDepartment = new ArrayList<User>(); 
    	ObjectManager viewEmployees = new ObjectManager(arralistOfEmployees, MsgEnum.VIEW_EMPLOYEES);
		ConnectionController.getClient().handleMessageFromClientUI(viewEmployees);
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
		
    	try {
			Thread.sleep(1500);
			for(User u : arralistOfEmployees) {
				String dep = u.getDepartment();
				if(dep.equals("Information Technology")) 
					inDepartment.add(u);
				else outSideDepartment.add(u);
	    	}
			List = FXCollections.observableArrayList(inDepartment);
			tblEmployees.setItems(List);
			List = FXCollections.observableArrayList(outSideDepartment);
			tblToAdd.setItems(List);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
			GuiManager.showSuccess(lblMsg, pMsg, "Department Updated Successfuly!");
			
			for(User u : changed) {
				if(inDepartment.contains(u))inDepartment.remove(u);
				else inDepartment.add(u);
				if(outSideDepartment.contains(u)) outSideDepartment.remove(u);
				else outSideDepartment.add(u);
			}
		}
	}
	
	
}

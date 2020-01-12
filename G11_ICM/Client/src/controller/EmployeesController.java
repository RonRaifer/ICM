package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.ClientConnector;
import common.MsgEnum;
import common.ObjectManager;
import entity.Employee;
import entity.Messages;
import entity.User;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EmployeesController implements Initializable{
	@FXML
	private TableView<User> tblEmployees;
	@FXML
	private TableView<User> tblToAdd;
	@FXML
	private TableColumn<User, String> col_id;
	@FXML
	private TableColumn<User, String> col_id1;
	@FXML
	private TableColumn<User, String> col_name1;
	@FXML
	private TableColumn<User, String> col_name;
	@FXML
	private TableColumn<User, String> col_department;
	@FXML
	private TableColumn<User, String> col_lastName;
	@FXML
	private TableColumn<User, User> col_options;

	
	public static ArrayList<User> arralistOfEmployees = null;
	private ObservableList<User> List = null;
	
	public static void setListOfEmployees(ArrayList<User> array) {
		arralistOfEmployees = new ArrayList<>(array);
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	ObjectManager viewEmployees = new ObjectManager(arralistOfEmployees, MsgEnum.VIEW_EMPLOYEES);
		ConnectionController.getClient().handleMessageFromClientUI(viewEmployees);
		col_id.setCellValueFactory(new PropertyValueFactory<>("iduser"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		col_department.setCellValueFactory(new PropertyValueFactory<>("Department"));
		col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName")); 	//type of employee. Worker/Information Engineer/Manager
		col_id1.setCellValueFactory(new PropertyValueFactory<>("iduser"));
		col_name1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		col_options.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		col_options.setCellFactory(param -> new TableCell<User, User>() { 
			@FXML
		    private final Button btn = new Button("Unfriend");

		    protected void updateItem(User user, boolean empty) {
		        super.updateItem(user, empty);

		        if (user == null) {
		            setGraphic(null);
		            return;
		        }

		        setGraphic(btn);
		        btn.setOnAction(
		            event -> {
		            	tblToAdd.getItems().add(user);
		            	getTableView().getItems().remove(user);
		            }
		        );
		    }
		});
    	try {
			Thread.sleep(1000);
			List = FXCollections.observableArrayList(arralistOfEmployees);
			tblEmployees.setItems(List);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

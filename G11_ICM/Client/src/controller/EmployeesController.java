package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.ClientConnector;
import common.MsgEnum;
import common.ObjectManager;
import entity.Employee;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class EmployeesController implements Initializable{
	@FXML
	private TableView<Employee> tblEmployees;
	@FXML
	private TableColumn<Employee, String> col_id;
	@FXML
	private TableColumn<Employee, String> col_name;
	@FXML
	private TableColumn<Employee, String> col_department;
	
	ClientConnector client;
	public static ArrayList<Employee> arralistOfEmployees = null;
	private ObservableList<Employee> list = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		client = ConnectionController.getClient();
    	ObjectManager viewEmployees = new ObjectManager(arralistOfEmployees, MsgEnum.VIEW_EMPLOYEES);
    	client.handleMessageFromClientUI(viewEmployees);
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	list = FXCollections.observableArrayList(arralistOfEmployees);
		
	}
}

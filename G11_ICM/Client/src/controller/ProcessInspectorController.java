package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.MsgEnum;
import common.ObjectManager;
import entity.ActionsNeeded;
import entity.Messages;
import entity.RequestHandling;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
	 
	 private static ArrayList<ActionsNeeded> arralistOfActions = null;
	 private static ObservableList<ActionsNeeded> List = FXCollections.observableArrayList();
		
	public static void setListOfActions(ArrayList<ActionsNeeded> array) {
		arralistOfActions = new ArrayList<>(array);
	}
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObjectManager viewActions = new ObjectManager(arralistOfActions, MsgEnum.VIEW_ACTIONS);
		ConnectionController.getClient().handleMessageFromClientUI(viewActions);
		col_requestId.setCellValueFactory(new PropertyValueFactory<>("idrequest"));
		col_chargeId.setCellValueFactory(new PropertyValueFactory<>("idCharge"));
		col_stage.setCellValueFactory(new PropertyValueFactory<>("stage"));
		col_actions.setCellValueFactory(new PropertyValueFactory<>("actionsNeeded"));
	    try {
			Thread.sleep(1000);
			if(arralistOfActions.isEmpty()) tabPane.getTabs().remove(tabWaitingActions);
			else {
				List = FXCollections.observableArrayList(arralistOfActions);
				tblActionsNeeded.setItems(List);
			}		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}    
}

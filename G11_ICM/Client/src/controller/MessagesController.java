package controller;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.ClientConnector;
import common.MsgEnum;
import common.ObjectManager;
import entity.Messages;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MessagesController implements Initializable
{
		@FXML
		private TableView<Messages> tblMessages;

	    @FXML
	    private TableColumn<Messages, String> dateColumn;

	    @FXML
	    private TableColumn<Messages, String> titleColumn;

	    @FXML
	    private TableColumn<Messages, String> optionsColumn;
	    
	    
	    private static ClientConnector client;
	    private static ObservableList<Messages> List = FXCollections.observableArrayList();
	    private static ArrayList<Messages> arralistofmessages;
	    
	    public static void setListOfMessages(ArrayList<Messages> array) {
	    	arralistofmessages = new ArrayList<>(array);
	    	
	    }
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
	    	ObjectManager viewMesages = new ObjectManager(LoginController.getLoggedUser(), MsgEnum.VIEW_MESSAGES);
	    	ConnectionController.getClient().handleMessageFromClientUI(viewMesages);
	    	
	    	dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateMessage"));
			titleColumn.setCellValueFactory(new PropertyValueFactory<>("titleMessage"));
			optionsColumn.setCellValueFactory(new PropertyValueFactory<>("idMessage"));
	    	try {
				Thread.sleep(1500);
				List = FXCollections.observableArrayList(arralistofmessages);
				tblMessages.setItems(List);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	    
	    
	    
	    
	    
}

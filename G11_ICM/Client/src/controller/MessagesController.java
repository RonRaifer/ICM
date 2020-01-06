package controller;


import java.util.ArrayList;

import common.ClientConnector;
import common.MsgEnum;
import common.ObjectManager;
import entity.Messages;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MessagesController
{
		@FXML
		private static TableView<Messages> tblMessages;

	    @FXML
	    private TableColumn<Messages, String> dateColumn;

	    @FXML
	    private TableColumn<Messages, String> titleColumn;

	    @FXML
	    private TableColumn<Messages, String> optionsColumn;
	    
	    
	    private static ClientConnector client;
	    private static ObservableList<Messages> List;
	    private static ArrayList<Messages> arralistofmessages;
	    
	    public static void setTable(User user) throws InterruptedException
	    {
	    	client = ConnectionController.getClient();
	    	ObjectManager viewMesages = new ObjectManager(user, MsgEnum.VIEW_MESSAGES);
	    	client.handleMessageFromClientUI(viewMesages);
	    	Thread.sleep(1000);
	    	 List = FXCollections.observableArrayList(arralistofmessages);
			
			//col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
			//col_board.setCellValueFactory(new PropertyValueFactory<>("board"));
			//col_handler.setCellValueFactory(new PropertyValueFactory<>("handler"));
			//col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
			
			tblMessages.setItems(List);	
		}
	    
	    
	    
	    
	    
}

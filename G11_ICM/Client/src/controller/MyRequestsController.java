package controller;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.ClientConnector;
import common.MsgEnum;
import common.ObjectManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MyRequestsController implements Initializable{
	 @FXML
	    private Label lblPageName;

	    @FXML
	    private Label lblPageNavigationName;

	    @FXML
	    private TextField tbId;

	    @FXML
	    private Button btnSearch;

	    @FXML
	    private Button btnClear;

	    @FXML
	    private TableView<?> requestTbl;

	    @FXML
	    private TableColumn<?, ?> idColumn;

	    @FXML
	    private TableColumn<?, ?> dateColumn;

	    @FXML
	    private TableColumn<?, ?> stageColumn;
	    
	    @FXML
	    private Label lblStageInfo;

	    
	    //attributes
	    private static ResultSet rsNotStarted;
	    private static ResultSet rsStarted;
	    
	    
	    
	    
	    @FXML
	    void clickClear(ActionEvent event) {
	    	tbId.clear();
	    }

	    @FXML
	    void clickSearch(ActionEvent event) {

	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			//sending id of user to server
			ObjectManager requestsMsg = new ObjectManager(LoginController.getLoggedUser().getIdUser(), MsgEnum.GET_REQUESTS_BY_ID);
			client.handleMessageFromClientUI(requestsMsg);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//taking care of started requests 
			try {
				
				//if the user doesnt have any requests, exiting this function
				if(!rsNotStarted.next()&&!rsStarted.next()) {
				requestTbl.setPlaceholder(new Label("You dont have any requests"));
					
					return;
				}
				
				while(rsStarted.next()) {
					
					//the column we will add to the table
					ArrayList<String> col = new ArrayList<>();
					
					//id column
					col.add(rsStarted.getString(1));
					
					//date column
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate localDate = LocalDate.now();
					col.add(dtf.format(localDate.plusDays(rsStarted.getLong(2))));
					
					//stage column
					col.add(rsStarted.getString(3));
					
					ObservableList obList = FXCollections.observableList(col);
					requestTbl.setItems(obList);
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
		}

		public static ResultSet getRs() {
			return rsNotStarted;
		}

		public static void setRsNotStarted(ResultSet rs) {
			MyRequestsController.rsNotStarted = rs;
		}
		public static ResultSet getRsStarted() {
			return rsStarted;
		}

		public static void setRsStarted(ResultSet rsStarted) {
			MyRequestsController.rsStarted = rsStarted;
		}

		private ClientConnector client = ConnectionController.getClient();


}

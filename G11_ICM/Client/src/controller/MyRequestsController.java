package controller;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import common.ClientConnector;
import common.MsgEnum;
import common.ObjectManager;
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

	    
	    //attributes
	    private static ResultSet rs;
	    private ClientConnector client = ConnectionController.getClient();
	    
	    
	    
	    @FXML
	    void clickClear(ActionEvent event) {
	    	tbId.clear();
	    }

	    @FXML
	    void clickSearch(ActionEvent event) {

	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			ObjectManager requestsMsg = new ObjectManager(LoginController.getLoggedUser().getIdUser(), MsgEnum.GET_REQUESTS_BY_ID);
			client.handleMessageFromClientUI(requestsMsg);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
		}


}

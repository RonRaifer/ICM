package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import boundary.GuiManager;
import common.MsgEnum;
import common.ObjectManager;
import entity.User;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class MenuController implements Initializable{
	@FXML
    private Button btnLogout;
    @FXML
    private Label lblName;
    @FXML
    private Label lblRole;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnMessages;
    @FXML
    private Button btnNewRequest;
    @FXML
    private Button btnMyRequests;
    @FXML
    private Button btnProcesses;
    @FXML
    private Button btnReports;
    @FXML
    private Button btnEmployees;
    @FXML
    private Hyperlink hlProfile;
    @FXML
    private AnchorPane apCenterContent;
    @FXML
    private BorderPane bpRoot;
    @FXML
    private Pane pRole;
    @FXML
    private Stage menuStage;
    private Button btnTemp;
	private User user;

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		btnTemp = new Button();
		sceneManager("HomePane", btnHome);
	}
	
	public void initData(User user, Stage stage) {
		this.user = user;
		menuStage = stage;
		lblName.setText(user.getFirstName() + " " + user.getLastName());
		lblRole.setText(user.getRole());
		lblRole.layoutXProperty().bind(pRole.widthProperty().subtract(lblRole.widthProperty()).divide(2));
		Platform.setImplicitExit(false);
		menuStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				e.consume();
				logoutUser();
			}	            	
        });
		
	}
	
	@FXML
    void homeClick(ActionEvent event) {
		sceneManager("HomePane", btnHome);
    }
	 
    @FXML
    void messagesClick(ActionEvent event) {
    	sceneManager("MessagesPane", btnMessages);
    }

    @FXML
    void myRequestsClick(ActionEvent event) {
    	sceneManager("MyRequestsPane", btnMyRequests);
    }

    @FXML
    void newRequestClick(ActionEvent event) {
    	sceneManager("NewRequestPane", btnNewRequest);
	}
    
    @FXML
    void logoutClick(ActionEvent event) {
    	logoutUser();
	}
    
    @FXML
    void processesClick(ActionEvent event) {
    	buttonStyle(btnProcesses);
    	if(user.getRole().compareTo("Inspector") == 0) { //if user is Inspector
    		sceneManager("ProcessPaneInspector", btnProcesses); 
    	}
    	else {
    		//sceneManager("ProcessesPane", btnProcesses);
    		FXMLLoader loader = new FXMLLoader();
    		try {
    			AnchorPane newLoadedPane;
    			loader.setLocation(controller.ProcessesController.class.getResource("/boundary/guifiles/ProcessesPane.fxml"));
    			newLoadedPane =  loader.load();
    			ProcessesController pController = loader.getController();
    			pController.initData(pController);
    			apCenterContent.getChildren().clear();
    			apCenterContent.getChildren().add(newLoadedPane);
    			} catch (IOException e) {
						e.printStackTrace();
				}		
    	}
    	
    }

    @FXML
    void reportsClick(ActionEvent event) {
    	sceneManager("ReportsPane", btnReports);
    }
    @FXML
    void employeesClick(ActionEvent event) {
    	sceneManager("EmployeesPane", btnEmployees);
    }
    
    @FXML
    void profileClick(ActionEvent event) {   	 
        try {
        	AnchorPane newLoadedPane; 
			newLoadedPane =  FXMLLoader.load(getClass().getResource("/boundary/guifiles/ProfilePane.fxml"));
			apCenterContent.getChildren().clear();
		    apCenterContent.getChildren().add(newLoadedPane);
		} catch (IOException ex) {
			Logger.getLogger(EnterController.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
	
	private void sceneManager(String fxmlName, Button button) {
		buttonStyle(button);
		try 
        {
            AnchorPane newLoadedPane;  
            newLoadedPane =  FXMLLoader.load(getClass().getResource("/boundary/guifiles/" + fxmlName + ".fxml"));
            apCenterContent.getChildren().clear();
            apCenterContent.getChildren().add(newLoadedPane);
        }
        catch (IOException ex) {
            Logger.getLogger(EnterController.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	/**
	 * Changes the style of button when pressed
	 * @param button The pressed button
	 */
	private void buttonStyle(Button button) {
		if(button.equals(btnTemp)) return;
		button.getStyleClass().add("btnClicked");
		btnTemp.getStyleClass().clear();
		btnTemp.getStyleClass().add("button");
		btnTemp = button;
	}
	private void logoutUser() {
		ColorAdjust adj = new ColorAdjust(0, -0.2, -0.1, 0);
        GaussianBlur blur = new GaussianBlur(55); // 55 is just to show edge effect more clearly.
        adj.setInput(blur);
        bpRoot.setEffect(adj);
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	alert.getButtonTypes().remove(ButtonType.OK);
    	alert.getButtonTypes().add(ButtonType.YES);
    	alert.setTitle("Exit ICM");
    	alert.setHeaderText("You are about to exit ICM");
    	alert.setContentText("Are you sure you want to exit?");
    	alert.showAndWait();
    	if (alert.getResult() == ButtonType.YES) {
    		ObjectManager msg = new ObjectManager(user, MsgEnum.LOGOUT);
    		ConnectionController.getClient().handleMessageFromClientUI(msg);
    		PauseTransition pause = new PauseTransition(Duration.seconds(2));
    		pause.setOnFinished(e -> {
    			try {
    				GuiManager.guiLoader("Enter", new Stage());
    				menuStage.close();
    			} catch (IOException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
    		});
    		pause.play();
    	}else {
    		bpRoot.setEffect(null);
    	}
		
	}
}

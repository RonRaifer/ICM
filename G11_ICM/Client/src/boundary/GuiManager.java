package boundary;

import java.io.IOException;

import controller.MenuController;
import entity.User;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class GuiManager {
	public static void guiLoader(String fxmlType) throws IOException {
		String path = "../boundary/guifiles/";
		FXMLLoader loader = new FXMLLoader();
		Stage stage = new Stage();	
		try {  
	            loader.setLocation(controller.MenuController.class.getResource(path + fxmlType));
				Pane requestsMain = loader.load();
	            Scene scene = new Scene(requestsMain);
	            stage.setScene(scene);
	            stage.setMaximized(true);
	            stage.setResizable(false);
	            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent e) {
						Platform.exit();
		                System.exit(0);
						
					}	            	
	            });
	            stage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	public static void guiLoader(String fxmlType, User user) throws IOException {
		String path = "../boundary/guifiles/";

		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		try {  
				loader.setLocation(controller.MenuController.class.getResource(path + fxmlType));
				Pane root = loader.load();
				MenuController controller = loader.getController();
				controller.initData(user);
	            Scene scene = new Scene(root);
	            stage.setScene(scene);
	            stage.setMaximized(true);
	            stage.setResizable(false);
	            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent e) {
						Platform.exit();
		                System.exit(0);
						
					}	            	
	            });
	            stage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	 public static void showError(Label lblError, Pane pError, String message) {
		 pError.setStyle("-fx-background-color: #FF5C5C;" + "-fx-background-radius: 20");
		 lblError.layoutXProperty().bind(pError.widthProperty().subtract(lblError.widthProperty()).divide(2));
	     lblError.setText(message);
		 pError.setVisible(true);
		 PauseTransition pause = new PauseTransition(Duration.seconds(5));
		 pause.setOnFinished(e -> pError.setVisible(false));
		 pause.play();
	}
	 
	public static void showSuccess(Label lblSuccess, Pane pSuccess, String message) {
		 pSuccess.setStyle("-fx-background-color: #01BF27;" + "-fx-background-radius: 20");
		 lblSuccess.layoutXProperty().bind(pSuccess.widthProperty().subtract(lblSuccess.widthProperty()).divide(2));
		 lblSuccess.setText(message);
		 pSuccess.setVisible(true);
		 PauseTransition pause = new PauseTransition(Duration.seconds(5));
		 pause.setOnFinished(e -> pSuccess.setVisible(false));
		 pause.play();
	}
}

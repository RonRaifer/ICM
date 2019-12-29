package boundary;

import java.io.IOException;

import common.Main;
import controller.MenuController;
import entity.User;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GuiManager {
	public static void guiLoader(String fxmlType) throws IOException {
		String path = "/boundary/guifiles/";
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
		String path = "/boundary/guifiles/";

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
}

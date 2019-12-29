package boundary;

import java.io.IOException;

import common.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GuiManager {
	public static void guiLoader(String fxmlType) throws IOException {
		String path = "/boundary/guifiles/";
		
		Stage stage = new Stage();	
		try {
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(controller.MenuController.class.getResource(path + fxmlType));
				Pane requestsMain = loader.load();
	            Scene scene = new Scene(requestsMain);
	            stage.setScene(scene);
	            stage.setResizable(false);
	            stage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
}

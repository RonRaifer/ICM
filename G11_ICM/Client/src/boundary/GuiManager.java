package boundary;

import java.io.IOException;

import common.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GuiManager {
	public static void guiLoader(String fxmlType,Button button) throws IOException {
		String path = "/boundary/guifiles/";
		Stage stage = (Stage)button.getScene().getWindow();
        Parent root = FXMLLoader.load(Main.class.getResource(path + fxmlType));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show(); 
	}
}

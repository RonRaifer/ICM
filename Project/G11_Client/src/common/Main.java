package common;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{
	private Pane InsidePane;
	private Button btnLogin;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/boundary/guifiles/Login.fxml"));
			Pane login = loader.load();
            Scene scene = new Scene(login);
			primaryStage.setTitle("ICM - Login");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	@FXML
    private void handleChangeView(ActionEvent event) {
        try {
            //tring menuItemID = ((MenuItem) event.getSource()).getId();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/boundary/guifiles/Login" + ".fxml"));
            loader.setController(this);
            InsidePane = loader.load();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}

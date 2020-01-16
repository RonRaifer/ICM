package common;


import java.net.InetAddress;
import java.net.UnknownHostException;

import controller.MenuController;
import controller.ServerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ServerMain  extends Application{
	
	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(controller.ServerController.class.getResource("/boundary/guifiles/Server.fxml"));
		Pane root = loader.load();
		ServerController controller = loader.getController();
		controller.initData(InetAddress.getLocalHost().getHostAddress().toString());
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/boundary/guifiles/img/icmLogo.png"));
        primaryStage.setTitle("ICM Server Dashboard");
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);  
        primaryStage.show();
	}

}

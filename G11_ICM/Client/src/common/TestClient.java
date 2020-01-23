package common;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import controller.NewRequestController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

class TestClient {

	private NewRequestController newRequest;
	@Test
	void wrongInput() {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/boundary/guifiles/NewRequestController.fxml"));
		try {
			Parent root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this constructor set the text box inside the controller
		newRequest = loader.getController();
		newRequest.setFields("Office", "", "","");
		
		Assertions.assertTrue(!newRequest.checkFormFields().equals("Input is ok"));
	}

}

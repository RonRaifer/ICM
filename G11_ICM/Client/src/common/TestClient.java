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
		
		
		newRequest = new NewRequestController();
		
		//empty input
		Assertions.assertTrue(!newRequest.checkFormFields("","","","").equals("Input is ok"));
	}

}

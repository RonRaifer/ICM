package junit;

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
	@Test
	void notFullInput() {
		newRequest= new NewRequestController();
		Assertions.assertFalse(newRequest.checkFormFields("Moodle","","asd","f").equals("Input is ok"));
	}
	@Test
	void notFullInput2()
	{
		newRequest= new NewRequestController();
		Assertions.assertTrue(newRequest.checkFormFields("Library","","","d").equals("The following fields are empty: Current state, Change. "));
	}
}

package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class LoginController {
	@FXML
    private Pane mainView;

    @FXML
    private void handleChangeView(ActionEvent event) {
        try {
            //tring menuItemID = ((MenuItem) event.getSource()).getId();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login" + ".fxml"));
            loader.setController(this);
            mainView = loader.load();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

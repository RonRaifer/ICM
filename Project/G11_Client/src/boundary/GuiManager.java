package boundary;

public class GuiManager {
	public static void screen(String fxmlName,Button button) throws IOException
	{
		String fxmlPath = "/boundary/guifiles/";
		Stage stage = (Stage)button.getScene().getWindow();
        Parent root = FXMLLoader.load(MainClient.class.getResource(fxmlPath + fxmlName));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show(); 
	} 
}

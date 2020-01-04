package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import common.IcmServer;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ServerController {
	 
	@FXML private TextArea taLog;
	@FXML private Label lblAddress;
	@FXML private Button btnConnection;
	
	private PrintStream ps ;
	private Console console;
	private IcmServer sv;
	private boolean isConnected = false;
	final public static int DEFAULT_PORT = 5555;
	
	@FXML 
	public void connectServer(ActionEvent event) throws InterruptedException, IOException {
		if(isConnected) {
			disconnectServer();
			return;
		}
	    Service<Void> service = new Service<Void>(){
	    @Override
	    protected Task<Void> createTask() {
	       return new Task<Void>() {
	          @Override
	         protected Void call() throws Exception {
	            console = new Console(taLog);	            	   
	            ps = new PrintStream(console, true);	            	   
	            System.setOut(ps);
	            System.setErr(ps);
	            
	            int port = 0; //Port to listen on
		       	try{port = 5555; } //Get port from command line
		       	catch(Throwable t) {port = DEFAULT_PORT;} //Set port to 5555

	    		sv = new IcmServer(port); //create the server with port
		        try {sv.listen();} //Start listening for connections
		        catch (Exception ex) {System.out.println("ERROR - Could not listen for clients!");}

	            return null;
	          }
	        };
	       }
	      }; service.setOnSucceeded(e -> {
	    	  isConnected = true;
	    	  setButtonConnectionState("Kill");
	    	  System.out.println("[Server Is ON]");
	      });
	   service.restart();     
	}
	
	public void initData(String address) {
    	lblAddress.setText(address); 
	}
	
	public void initialize(URL location, ResourceBundle resources) throws UnknownHostException {
		
	}
	
	/***
	 * Internal Class to redirect console output to TextArea
	 * 
	 */
	public class Console extends OutputStream {
		private TextArea console;
		private String b = "";
	    
		public Console(TextArea console) {
			this.console = console;
	    }

	    public void appendText(String valueOf) {
	    	Platform.runLater(() -> {
	    		console.appendText("<ICM>: " + valueOf);});   
	    }
	    /***
		 * Build string from bytes sent by prinln and passes it to appendText
		 * 
		 * @param i The byte
		 */
	    public void write(int i) throws IOException {
	    	b += (String.valueOf((char) i));
	    	if((String.valueOf((char) i)).equals("\n")) {
	    		appendText(b);
	    		b = "";
	    	}
	    }
	}
	
	/***
	 * Changes button name and icon by state (Start/Kill).
	 * 
	 * @param state The name of state to show on button
	 */
	private void setButtonConnectionState(String state) {
		Image iconState = new Image(getClass().getResourceAsStream("../boundary/guifiles/icons/" + state +  ".png"));
		ImageView iv = new ImageView(iconState);
		iv.setFitWidth(40);
		iv.setFitHeight(40);
		btnConnection.setText(state + " Server");
		btnConnection.setGraphic(iv);
	}
	/***
	 * Close db and server connections. Happens when button kill server is clicked 
	 * 
	 */
	private void disconnectServer() {
   		try {
   			sv.stopListening();
			sv.close();
			sv.getConnection().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
   		catch (SQLException e) {
			e.printStackTrace();
		}
   		isConnected = false;
   		setButtonConnectionState("Start");
   		System.out.println("[Server Is OFF]");
	}
}

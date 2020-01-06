package common;

import java.sql.SQLException;

import controller.LoginController;
import controller.MyRequestsController;
import controller.NewRequestController;

//hanler for messages received from server
public class msgReceivedHandler {	
	
	public static void msgHandler(Object msg) {
		ObjectManager objectManager = (ObjectManager)msg;
		switch(objectManager.getMsgEnum()) 
		{
		case LOGIN:
			LoginController.setUserReceived(objectManager.getUser());
			break;
		case LOGIN_ERROR:
			LoginController.errorMessage = objectManager.getError();
			break;
		case SEND_ID_OF_REQUEST_TO_CLIENT:
		
			NewRequestController.setNewID(objectManager.getReqIDFromServer());
			break;
		
		case SEND_RS_NOT_STARTED_TO_CLIENT:
			
			MyRequestsController.setRsNotStarted(objectManager.getRs());
			break;
		
		case SEND_RS_STARTED_TO_CLIENT:
			try {
				System.out.println(objectManager.getRs().next());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MyRequestsController.setRsStarted(objectManager.getRs());
			break;
			
		default:
			break;
		}
	}
}

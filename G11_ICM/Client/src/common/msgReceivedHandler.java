package common;

import controller.LoginController;
import controller.MessagesController;
import controller.NewRequestController;

//hanler for messages received from server
public class msgReceivedHandler {	
	
	public static void msgHandler(Object msg) throws InterruptedException {
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
		case	SEND_MESSAGES_TO_CLIENT:
			MessagesController.setTable(objectManager.getUser());
			
		default:
			break;
		}
	}
}

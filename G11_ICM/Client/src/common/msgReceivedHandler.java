package common;

import controller.LoginController;

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
		default:
			break;
		}
	}
}

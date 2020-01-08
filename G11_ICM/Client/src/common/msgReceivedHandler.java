package common;

import java.sql.SQLException;
import java.util.ArrayList;

import controller.EmployeesController;
import controller.LoginController;

import controller.MessagesController;

import controller.MyRequestsController;

import controller.NewRequestController;
import entity.Messages;
import entity.Request;
import entity.User;

//hanler for messages received from server
public class msgReceivedHandler {	
	
	@SuppressWarnings("unchecked")
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

		case SEND_MESSAGES_TO_CLIENT:
			//MessagesController.setTable(objectManager.getUser());
			MessagesController.setListOfMessages((ArrayList<Messages>)objectManager.getArray());
			break;
		
		case SEND_RS_NOT_STARTED_TO_CLIENT:		
			
			MyRequestsController.setRsNotStarted( (ArrayList<Request>) objectManager.getArray());
			break;
		
		case SEND_RS_STARTED_TO_CLIENT:
			
			MyRequestsController.setRsStarted( (ArrayList<Request>) objectManager.getArray());
			break;
			
		case VIEW_EMPLOYEES:
			EmployeesController.setListOfEmployees((ArrayList<User>)objectManager.getArray());
			break;
			
		default:
			break;
		}
	}
}

package common;

import java.sql.SQLException;
import java.util.ArrayList;

import controller.EmployeesController;
import controller.EvaluationController;
import controller.LoginController;

import controller.MessagesController;

import controller.MyRequestsController;

import controller.NewRequestController;
import controller.ProcessInspectorController;
import controller.ProcessesController;
import controller.RequestViewController;
import controller.ReviewController;
import entity.ActionsNeeded;
import entity.Messages;
import entity.Request;
import entity.RequestHandling;
import entity.User;
import sun.reflect.generics.visitor.Reifier;

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
			LoginController.errorMessage = objectManager.getMsgString();
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
		case VIEW_ACTIONS:
			ProcessInspectorController.setListOfActions((ArrayList<ActionsNeeded>)objectManager.getArray());
			break;
		case VIEW_PROCESSES:
			ProcessesController.setListOfProcesses((ArrayList<RequestHandling>)objectManager.getArray());
			break;
		case VIEW_PROCESSES_TO_BE_DETERMINED:
			ProcessesController.setListOfTimeRequests((ArrayList<RequestHandling>)objectManager.getArray());
			break;	
		case VIEW_REQUEST: //Gets Request by the idrequest send by user
			RequestViewController.setRequest((Request)objectManager.getReques());
			break;
			
		case CLIENT_EV_REP:
			EvaluationController.setReport(objectManager.getEvReport());
			break;
			
		case SET_EV_IN_REVIEW:
			ReviewController.setReport(objectManager.getEvReport());
			break;
		default:
			break;
		}
	}
}

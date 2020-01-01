package common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

import entity.User;
import common.ObjectManager;
import ocsf.server.ConnectionToClient;

public class MsgHandler {
	DBHandler dbHandler = IcmServer.getDBHandler();
	private ObjectManager objectManager;
	
	public void clientMsgHandler(Object msg, ConnectionToClient client, Connection conn) throws IOException, SQLException {
		objectManager = (ObjectManager)msg;
		User user = null;
		String query;
		String errorMsg;
		ResultSet rs;
		switch (objectManager.getMsgEnum()) {
		case LOGIN:
			query = "SELECT * FROM user WHERE iduser = '" + objectManager.getUser().getIdUser() + "'" + ";";
			rs = dbHandler.executeQ(query);
			if (rs.next() == true) {
				if (rs.getString("password").equals(objectManager.getUser().getPassword())) { 	//if user and password OK
					if(!IcmServer.addToConnectedUsers(rs.getString("iduser"))) {				//if user already connected - show error message and break
						errorMsg = "User ID: " + rs.getString("iduser") +  " already connected to ICM.";
						objectManager = new ObjectManager(errorMsg, MsgEnum.LOGIN_ERROR);
					}else {
						if(rs.getString(6).equals("Information Technology")) {
							ResultSet rs2;
							query = "SELECT * FROM employee WHERE iduser = '" + objectManager.getUser().getIdUser() + "'" + ";";
							rs2 = dbHandler.executeQ(query);
							if (rs2.next() == true) { //if user is an employee
								if(rs2.getString("role") == null) { //if employee does not have specific role
									user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs2.getString(2), rs.getString(6), rs.getString(7)); //put profession role
								}
								else user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs2.getString("role"), rs.getString(6), rs.getString(7)); //put role in system
							}
						}else {
							user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
						}
						objectManager = new ObjectManager(user, MsgEnum.LOGIN);
					}
				} else {
					objectManager = new ObjectManager("Password invalid", MsgEnum.LOGIN_ERROR);
				}
			}
			else {
				objectManager = new ObjectManager("User does not exist", MsgEnum.LOGIN_ERROR);
			}
			client.sendToClient(objectManager);
			break;
			
		case LOGOUT:
			IcmServer.removeUserConnected(objectManager.getUser().getIdUser()); //remove user from connected list
			break;
			
		default:
			break;
		}
	}
}

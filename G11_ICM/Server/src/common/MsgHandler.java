package common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import entity.User;
import common.ObjectManager;
import ocsf.server.ConnectionToClient;

public class MsgHandler {
	DBHandler dbHandler = IcmServer.getDBHandler();
	ObjectManager objectManager;
	public void clientMsgHandler(Object msg, ConnectionToClient client, Connection conn) throws IOException, SQLException {
		objectManager = (ObjectManager)msg;
		
		User user = null;
		String query;
		ResultSet rs;
		
		switch (objectManager.getMsgEnum()) {
		case LOGIN:
			query = "SELECT * FROM user WHERE iduser = '" + objectManager.getUser().getIdUser() + "'" + ";";
			rs = dbHandler.executeQ(query);
			if (rs.next() == true) {
				if (rs.getString("password").equals(objectManager.getUser().getPassword())) {
					user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
					objectManager = new ObjectManager(user, objectManager.getMsgEnum().LOGIN);
				} else {
					objectManager = new ObjectManager("Password invalid", objectManager.getMsgEnum().LOGIN_ERROR);
				}
			}
			else {
				objectManager = new ObjectManager("User does not exist", objectManager.getMsgEnum().LOGIN_ERROR);
			}
			client.sendToClient(objectManager);
			break;
			
		default:
			break;
		}
	}
}

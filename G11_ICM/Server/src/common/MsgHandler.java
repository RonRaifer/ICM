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
	public void clientMsgHandler(Object msg, ConnectionToClient client, Connection conn) throws IOException {
		objectManager = (ObjectManager)msg;
		User user = null;
		
		String query;
		Statement stmt;
		ResultSet rs;
		try {
			stmt = conn.createStatement();
			switch (objectManager.getMsgEnum()) {
			case LOGIN:
				query = "SELECT * FROM user WHERE iduser = '" + objectManager.getUser().getIdUser() + "'" + ";";
				rs = stmt.executeQuery(query);
				
				if (rs.next() == true) {
					if (rs.getString("password").equals(objectManager.getUser().getPassword())) {
						user = new User(objectManager.getUser().getIdUser(), rs.getString("password"));
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

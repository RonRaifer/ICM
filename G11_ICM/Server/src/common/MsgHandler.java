package common;

import java.sql.Connection;

import ocsf.server.ConnectionToClient;

public class MsgHandler {
	DBHandler dbHandler = IcmServer.getDBHandler();
	private ObjectManager objectManager;
	public void clientMsgHandler(Object msg, ConnectionToClient client, Connection conn) {
		
	}
}

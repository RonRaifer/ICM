package common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

import entity.Document;
import entity.Messages;
import entity.User;
import common.ObjectManager;
import ocsf.server.ConnectionToClient;

public class MsgHandler {
	DBHandler dbHandler = IcmServer.getDBHandler();
	ObjectManager objectManager;
	public String idOfRequestFromServer;

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
			rs.close();
			break;
			
		case LOGOUT:
			IcmServer.removeUserConnected(objectManager.getUser().getIdUser()); //remove user from connected list
			
			break;
			
		case ADD_REQUEST:
			
			//getting max id to set the new id
			rs = dbHandler.executeQ("SELECT MAX(idrequest) FROM request");
			int rowcount;
			
			if(!rs.next())
				rowcount = 0;
			else
				rowcount = rs.getInt(1);
			
			
			//sets the request-object id
			objectManager.getReques().setIdReq(String.valueOf(rowcount+1));
			
			
			idOfRequestFromServer = new Integer(rowcount).toString();
			//building the insert query
			StringBuilder qr = new StringBuilder("INSERT INTO request VALUES(");
			for(String fields : objectManager.getReques().getAll()) {
				
					qr.append("'"+fields+"',");
			}
			
			qr.setCharAt(qr.length()-1, ')');
			
			//executing the query
			dbHandler.executeUpdate(qr.toString());
			System.out.println("Request added to request table\n");
			client.sendToClient(new ObjectManager(new Integer(rowcount),MsgEnum.SEND_ID_OF_REQUEST_TO_CLIENT));
			//sending the id of the request to the client
			
			
			break;
		case ADD_FILES: 
			
			//files part
			//adding the files to the documents table, and saving them on server side
			//creating the id for the files
			rs = dbHandler.executeQ("SELECT MAX(iddocument) FROM document");
			
			if(!rs.next())
				rowcount = 0;
			else
				rowcount = rs.getInt(1);
			
			//building the query
			int indexOfFile = 0;
			
			for(Document dcm : objectManager.getListOfFiles()) {

				StringBuilder filesQuery = new StringBuilder("INSERT INTO document VALUES(");
				//first thing we set it's id
				dcm.setIddocument(Integer.toString(++rowcount));
				
				//now we build the query
				filesQuery.append("'"+dcm.getIddocument()+"',");//ok
				
				filesQuery.append("'"+dcm.getIdrequest()+"_"+(++indexOfFile)+"',"); //format of naming: requestid_number
				filesQuery.append("'"+dcm.getFileType()+"',");//ok
				filesQuery.append("'"+"C:/ICM/Documents/"+dcm.getIdrequest()+"_"+indexOfFile+"."+dcm.getFileType()+"/"+"',");
				filesQuery.append("'"+dcm.getIdrequest()+"')");
				
				//executing the query
				
				dbHandler.executeUpdate(filesQuery.toString());
				
				
				//saving the files in server side
				
				//create the path if it doesnt exist
				File dir = new File("C:/ICM/Documents/"+dcm.getIdrequest()+"/");
				 if (!dir.exists()) dir.mkdirs();
				 
				 //copying the file
				 
				 MyFile ff = dcm.getMyFile();
					
					
					try {
						String serverFilesPath = "C:/ICM/Documents/"+dcm.getIdrequest()+"/"+dcm.getIdrequest()+"_"+(indexOfFile)+"."+dcm.getFileType();
						FileOutputStream fos = new FileOutputStream(serverFilesPath);
						BufferedOutputStream bos = new BufferedOutputStream(fos);
						bos.write(ff.getMybytearray(), 0, ff.getSize());
						bos.close();
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 
				
				
			}
			System.out.println("File added sucessefuly");
			break;
			
		case VIEW_MESSAGES: 	
			query = "SELECT * FROM Messages WHERE iduser = '" + objectManager.getUser().getIdUser() + "'" + ";";
			rs = dbHandler.executeQ(query);
			if (rs.next()==false)
			{
				break;
			}
			client.sendToClient(new ObjectManager(rsToCollection(rs),MsgEnum.SEND_MESSAGES_TO_CLIENT));
			
			
			break;
		default:
			break;
		}
	}
	public ArrayList<ArrayList<String>> rsToCollection(ResultSet rs){
	       
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
       
       
       
        try {
            //case of empty rs
            if(!rs.next())
                return result;
           
            while(rs.next()) {
                ArrayList<String> temp = new ArrayList<String>();
                for(int i = 1; i<rs.getMetaData().getColumnCount();i++) {
                    temp.add(rs.getString(i));
                }
               
                result.add(temp);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        return result;
       
    }
}

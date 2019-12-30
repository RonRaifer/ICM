package common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class IcmServer extends AbstractServer 
{	
	 private MsgHandler msgHandler;
	 private static Connection conn;
	 private static DBHandler dbHandler;
 
  /**
   * The default port to listen on.
   */
	 final public static int DEFAULT_PORT = 5555;
  
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
	  public IcmServer(int port) 
	  {
	    super(port);
	  }

  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
	public void handleMessageFromClient(Object msg, ConnectionToClient client)
	{
		msgHandler = new MsgHandler();
		
		try {
			msgHandler.clientMsgHandler(msg, client, conn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  
	protected void serverStarted()
	{
	  System.out.println
	    ("Server listening for connections on port " + getPort());
	}
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
  }
  
  /**
   * 
   */
  public static DBHandler getDBHandler() {
  	return dbHandler;
  }


  /**
   * 
   */
  public MsgHandler getMsgHandler() {
  	return msgHandler;
  }


  /**
   * 
   */
  public static void setDBHandler(DBHandler Handler) {
  	dbHandler = Handler;
  }


  /**
   * 
   */
  public static void setConnection(Connection con) {
  	conn = con;
  }
  
  /**
   * This method is responsible for the creation of 
   * the server instance (there is no UI in this phase).
   *
   * @param args[0] The port number to listen on.  Defaults to 5555 
   *          if no argument is entered.
   */
  public static void main(String[] args) 
  {
    int port = 0; //Port to listen on
   
    dbHandler = new DBHandler();
    conn = dbHandler.databaseConnect();
    try
    {
      port = Integer.parseInt(args[0]); //Get port from command line
    }
    catch(Throwable t)
    {
      port = DEFAULT_PORT; //Set port to 5555
    }
	
    IcmServer sv = new IcmServer(port);
    
    try 
    {
      sv.listen(); //Start listening for connections
    } 
    catch (Exception ex) 
    {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }
}
//End of EchoServer class
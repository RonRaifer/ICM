package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHandler {
	private Connection conn;

	public Connection databaseConnect()
	{
		try 
		{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {/* handle the error*/}
        
        try 
        {
        	//on develop
        	conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com/q3t6Vm6bTC?autoReconnect=true&useSSL=false","q3t6Vm6bTC","hLwAfZ78Fx");
        	//For Local:
            //conn = DriverManager.getConnection("jdbc:mysql://localhost/OBLdb?autoReconnect=true&useSSL=false","root","Aa123456");
            System.out.println("SQL connection succeed");
     	} catch (SQLException ex) 
     	    {	/* handle any errors*/
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            } 
        return conn;
   	}
	public void AAA() {
		Statement stmt;
		  try 
			{
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM user;");
				while(rs.next()){
					System.out.println(" "+ rs.getString(1) + "  " + rs.getString(2));
				}
				rs.close();
				conn.close();

			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
	}
}

package common;

import java.io.IOException;
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
	public ResultSet executeQ(String query){
		Statement stmt;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	public void executeUpdate(String query) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

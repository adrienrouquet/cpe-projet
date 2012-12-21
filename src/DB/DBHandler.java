package DB;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.sql.Connection;


public class DBHandler{
	
	private String _dbName = "";
	private Properties _connectionProps = null;
	
	public DBHandler(String dbName)
	{
		_dbName = dbName;
		_connectionProps = new Properties();
	    _connectionProps.put("user", "root");
	    _connectionProps.put("password", "password");
	   
	   
	    try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			System.err.println("Error in DBHandler constructor: " + e.getMessage());
			e.printStackTrace();
		}
	   
			    
	}
	
	public Connection getConn()
	{ 
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+_dbName, _connectionProps);
		} catch (Exception e) {			
			System.err.println("Error in getConn: " + e.getMessage());
		}
		return conn;
	}
	
	public ResultSet executeQueryRS(String query)
	{
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try
		{
			conn = getConn();
			statement = conn.createStatement();	
			rs = statement.executeQuery(query);

		}
		catch (Exception e) {
			System.err.println("Error in executeQueryRS:" + e.getMessage());
		}
		finally {
			closeConn(conn);			
		}
		return rs;
	}
	
	public boolean executeQuery(String query)
	{
		Connection conn = null;
		Statement statement = null;
		try
		{
			conn = getConn();
			statement = conn.createStatement();	
			statement.executeUpdate(query);
			return true;

		}catch (Exception e) {
			System.err.println("Error in executeQuery:" + e.getMessage());
		}
		return false;
	}
	
	public boolean closeConn(Connection conn)
	{
		try {
			conn.close();
			return true;
		}catch (Exception e) {
			System.err.println("Error in closeConn:" + e.getMessage());
		}
		return false;
		
	}
}
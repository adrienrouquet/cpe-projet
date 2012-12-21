package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import Bean.User;
import DB.DBHandler;


/**
 * Class DBUserToolbox
 */
public class DBUserToolbox extends DBToolbox {
	
	public DBUserToolbox ()
	{
		super();
		_dbName = "cpe-projet_db";
		_dbHandler = new DBHandler(_dbName);
	}

	
	public ArrayList<User> getUsers()
	{
		ArrayList<User> users 	= new ArrayList<User>();
		
		Connection conn 		= getConn();
		ResultSet rs 			= executeQueryRS(conn, "CALL getUsers()");
				
		try {
			while(rs.next())
			{
				users.add(new User(rs.getInt("id"),rs.getString("login"),rs.getString("email"),rs.getString("phone"),rs.getString("firstName"),rs.getString("lastName"),rs.getTimestamp("lastLoginDate")));
			}
		} catch (SQLException e) {
			System.out.println("Error in getUsers:" +e.getMessage());
		}
		
		closeConn(conn);
		
		return users;
	}
	
	public String getName(Integer id)
	{
		String name 	= "";
		
		Connection conn = getConn();
		ResultSet rs 	= executeQueryRS(conn, "CALL getName(" + id + ")");
		
		try {
			while(rs.next())
			{
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			System.out.println("Error in getName:" +e.getMessage());
		}
		
		closeConn(conn);
		
		return name;
	}
	
	public Timestamp getLastLogin(Integer id)
	{
		Timestamp lastLogin = null;
		
		Connection conn 	= getConn();
		ResultSet rs 		= executeQueryRS(conn, "CALL getLastLogin(" + id + ")");
		
		try {
			while(rs.next())
			{
				lastLogin = rs.getTimestamp("lastLoginDate");
			}
		} catch (SQLException e) {
			System.out.println("Error in getLastLogin:" +e.getMessage());
		}
		
		closeConn(conn);
		
		return lastLogin;
	}
	
	public boolean checkCredentials(String login, String password)
	{
		boolean match 	= false;
		
		Connection conn = getConn();
		ResultSet rs 	= executeQueryRS(conn, "CALL checkCredentials('" + login + "','" + password + "')");
		
		try {
			while(rs.next())
			{
				match = rs.getBoolean("valid");
			}
		} catch (SQLException e) {
			System.out.println("Error in checkCredentials:" +e.getMessage());
		}
		
		closeConn(conn);
		
		return match;
	}
	
	public int getIdFromLogin(String login)
	{
		int id	 		= 0;
		
		Connection conn = getConn();
		ResultSet rs 	= executeQueryRS(conn, "CALL getIdFromLogin('" + login + "')");
		
		try {
			while(rs.next())
			{
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			System.out.println("Error in getIdFromLogin:" +e.getMessage());
		}
		
		closeConn(conn);
		
		return id;
	}
	
	public User getUser(int id)
	{
		User user 		= null;
		
		Connection conn = getConn();
		ResultSet rs 	= executeQueryRS(conn, "CALL getUser(" + id + ")");
		
		try {
			rs.first();
			user = new User(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("email"),rs.getString("phone"),rs.getString("firstName"),rs.getString("lastName"),rs.getTimestamp("lastLoginDate"));
		} catch (SQLException e) {
			System.out.println("Error in getUser:" +e.getMessage());
		}
		
		closeConn(conn);
		
		return user;
	}
	
	public User getUser(String login)
	{
		return getUser(getIdFromLogin(login));
	}

}

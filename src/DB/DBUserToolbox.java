package DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import Bean.UserBean;
import DB.DBHandler;
import Class.User;

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

	public String userExists(String email, String phone, String login)
	{
		
		Connection conn 			= getConn();
		CallableStatement cs 		= null;
		ResultSet rs 				= null;
		String exists				= "";
		try {
			
			cs = conn.prepareCall("{CALL userExists(?,?,?)}");
			cs.setString("pEmail", email);
			cs.setString("pPhone", phone);
			cs.setString("pLogin", login);
			rs = cs.executeQuery();		
			
			while(rs.next())
			{
				if(rs.getBoolean("loginExists"))
				{
					exists+="loginExists,";
				}
				if(rs.getBoolean("phoneExists"))
				{
					exists+="phoneExists,";
				}
				if(rs.getBoolean("emailExists"))
				{
					exists+="emailExists,";
				}
			}
		} catch (SQLException e) {
			System.out.println("Error in userExists:" +e.getMessage());
		}
		
		closeConn(conn);
		
		return exists;
	}
	
	public ArrayList<User> getUsers()
	{
		
		Connection conn 		= getConn();
		CallableStatement cs 	= null;
		ResultSet rs 			= null;
		ArrayList<User> users 	= null;
		
		try {
			
			cs = conn.prepareCall("{CALL getUsers()}");
			rs = cs.executeQuery();
			
			users = new ArrayList<User>();
			
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
	
	public ArrayList<User> getContacts(int id)
	{
		
		Connection conn 		= getConn();
		CallableStatement cs 	= null;
		ResultSet rs 			= null;
		ArrayList<User> users 	= null;
		
		try {
			cs 		= conn.prepareCall("{CALL getContacts(?)}");
			cs.setInt("pUserId", id);
			rs 		= cs.executeQuery();
			
			users = new ArrayList<User>();
			
			while(rs.next())
			{
				users.add(new User(rs.getInt("id"),rs.getString("login"),rs.getString("email"),rs.getString("phone"),rs.getString("firstName"),rs.getString("lastName"),rs.getTimestamp("lastLoginDate")));
			}
		} catch (SQLException e) {
			System.out.println("Error in getContacts:" +e.getMessage());
		}
		
		closeConn(conn);
		
		return users;
	}
	
	public ArrayList<User> findContacts(String name, String login, String email, String phone)
	{
		
		Connection conn 		= getConn();
		CallableStatement cs 	= null;
		ResultSet rs 			= null;
		ArrayList<User> users 	= null;
		
		try {
			cs 		= conn.prepareCall("{CALL findContacts(?,?,?,?)}");
			cs.setString("pName", name);
			cs.setString("pLogin", login);
			cs.setString("pEmail", email);
			cs.setString("pPhone", phone);
			rs 		= cs.executeQuery();
			
			users = new ArrayList<User>();
			
			while(rs.next())
			{
				users.add(new User(rs.getInt("id"),rs.getString("login"),rs.getString("email"),rs.getString("phone"),rs.getString("firstName"),rs.getString("lastName"),rs.getTimestamp("lastLoginDate")));
			}
		} catch (SQLException e) {
			System.out.println("Error in getContacts:" +e.getMessage());
		}
		
		closeConn(conn);
		
		return users;
	}

	public void addUser(String firstName, String lastName, String email, String phone, String login, String password)
	{
		
		Connection conn 		= getConn();
		CallableStatement cs 	= null;
		
		try {
			
			cs = conn.prepareCall("{CALL addUser(?,?,?,?,?,?)}");
			cs.setString("pFirstName", firstName);
			cs.setString("pLastName", lastName);
			cs.setString("pEmail", email);
			cs.setString("pPhone", phone);
			cs.setString("pLogin", login);
			cs.setString("pPassword", password);
			cs.executeQuery();			
			
		} catch (SQLException e) {
			System.out.println("Error in addUser:" +e.getMessage());
		}
		
		closeConn(conn);
	}
	
	public void addContact(int srcUserId, int dstUserId)
	{
		
		Connection conn 		= getConn();
		CallableStatement cs 	= null;
		
		try {
			
			cs = conn.prepareCall("{CALL addContact(?,?)}");
			cs.setInt("pSrcUserId", srcUserId);
			cs.setInt("pDstUserId", dstUserId);
			cs.executeQuery();			
			
		} catch (SQLException e) {
			System.out.println("Error in addContact:" +e.getMessage());
		}
		
		closeConn(conn);
	}
	
	public String getName(Integer id)
	{
		Connection conn 		= getConn();
		CallableStatement cs 	= null;
		ResultSet rs 			= null;
		String name 			= "";
		
		try {
			
			cs = conn.prepareCall("{CALL getName(?)}");
			cs.setInt("pUserId", id);		
			rs = cs.executeQuery();	
			
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
	
	public String getLogin(Integer id)
	{
		Connection conn 		= getConn();
		CallableStatement cs 	= null;
		ResultSet rs 			= null;
		String name 			= "";
		
		try {
			
			cs = conn.prepareCall("{CALL getLogin(?)}");
			cs.setInt("pUserId", id);		
			rs = cs.executeQuery();	
			
			while(rs.next())
			{
				name = rs.getString("login");
			}
		} catch (SQLException e) {
			System.out.println("Error in getLogin:" +e.getMessage());
		}
		
		closeConn(conn);
		
		return name;
	}
	
	public Timestamp getLastLogin(Integer id)
	{
		
		Connection conn 		= getConn();
		CallableStatement cs 	= null;
		ResultSet rs 			= null;
		Timestamp lastLogin 	= null;
		
		try {
			
			cs = conn.prepareCall("{CALL getLastLogin(?)}");
			cs.setInt("pUserId", id);		
			rs = cs.executeQuery();
			
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
		
		Connection conn 		= getConn();
		CallableStatement cs 	= null;
		ResultSet rs 			= null;
		
		try {
			
			cs = conn.prepareCall("{CALL checkCredentials(?,?)}");
			cs.setString("pLogin", login);
			cs.setString("pPassword", password);
			rs = cs.executeQuery();
			
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
		
		Connection conn 		= getConn();
		CallableStatement cs 	= null;
		ResultSet rs 			= null;
		int id	 				= 0;
		
		try {
			
			cs = conn.prepareCall("{CALL getIdFromLogin(?)}");
			cs.setString("pLogin", login);
			rs = cs.executeQuery();
			
			
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
		
		
		Connection conn 		= getConn();
		CallableStatement cs 	= null;
		ResultSet rs 			= null;
		User user 				= null;
		
		try {
			
			cs = conn.prepareCall("{CALL getUser(?)}");
			cs.setInt("pUserId", id);
			rs = cs.executeQuery();
			
			while(rs.next())
			{
				user = new User(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("email"),rs.getString("phone"),rs.getString("firstName"),rs.getString("lastName"),rs.getTimestamp("lastLoginDate"));
			}
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

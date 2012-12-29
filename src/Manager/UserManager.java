package Manager;

import java.sql.Timestamp;
import java.util.ArrayList;

import Bean.User;
import DB.DBUserToolbox;


/**
 * Class UserManager
 */
public abstract class UserManager {

	private static DBUserToolbox _dbut = new DBUserToolbox();

	public UserManager(){};


	public static ArrayList<User> getUsers()
	{
		return _dbut.getUsers();
	}
	
	public static ArrayList<User> getContacts(int id)
	{
		return _dbut.getContacts(id);
	}

	public static String getName(int id)
	{

		String name = _dbut.getName(id);
		if(name == null)
			return "N/A";
		return name;
	}
	
	public static Timestamp getLastLogin(int id)
	{

		return _dbut.getLastLogin(id);
	}  	

}

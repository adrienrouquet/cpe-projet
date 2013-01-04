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
	private static ArrayList<User> _usersConnected = new ArrayList<User>();

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


	public static ArrayList<User> getUsersConnected() {
		return _usersConnected;
	}


	public static void setUsersConnected(ArrayList<User> _usersConnected) {
		UserManager._usersConnected = _usersConnected;
	}

	public static void addUserConnected(User user) {
		_usersConnected.add(user);
		System.out.println("User"+ user.getId() + "("+ getName(user.getId()) +") is connected");
	}
	
	public static void delUserConnected(User user) {
		_usersConnected.remove(user);
		System.out.println("User"+ user.getId() + "("+ getName(user.getId()) +") is disconnected");
	}
	
	public static User getConnectedUser(Integer id) {
		for (User user : _usersConnected) {
			if (id.equals(user.getId())) {
				return user;
			}
		}
		return null;
	}
}

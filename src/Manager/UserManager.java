package Manager;

import java.util.ArrayList;

import Class.User;
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
	
//	public static String getName(int id)
//	{
//		String name = _dbut.getName(id);
//		if(name == null)
//			return "N/A";
//		return name;
//	}
//	
//	public static Timestamp getLastLogin(int id)
//	{
//		return _dbut.getLastLogin(id);
//	}

	public static void setUsersConnected(ArrayList<User> _usersConnected) {
		UserManager._usersConnected = _usersConnected;
	}

	public static ArrayList<User> getUsersConnected() {
		return _usersConnected;
	}

	public static ArrayList<User> getUsersConnected(Integer id) {
		ArrayList<User> users = new ArrayList<User>();
		for (User user : _usersConnected) {
			if (id.equals(user.getId())) {
				users.add(user);
			}
		}
		return users;
	}

	public static void addUserConnected(User user) {
		_usersConnected.add(user);
		System.out.println("User"+ user.getId() + "("+ user.getName() +") is connected");
	}
	
	public static void delUserConnected(User user) {
		_usersConnected.remove(user);
		System.out.println("User"+ user.getId() + "("+ user.getName() +") is disconnected");
	}
	
//	public static void addContact(int srcUserId, int dstUserId)
//	{
//		_dbut.addContact(srcUserId, dstUserId);
//	}
	
	public static ArrayList<User> findContacts(String name, String login, String email, String phone) {
		return _dbut.findContacts(name, login, email, phone);
	}
	
	public static User getUser(int id) {
		return _dbut.getUser(id);
	}
}

package Manager;

import java.util.ArrayList;
import java.util.HashMap;

import Class.User;
import Class.Websocket;
import DB.DBUserToolbox;


/**
 * Class UserManager
 */
public abstract class UserManager {

	private static DBUserToolbox _dbut = new DBUserToolbox();
	private static ArrayList<User> _usersConnected = new ArrayList<User>();
//	private static HashMap<String, User> _usersConnected = new HashMap<String, User>();

	public UserManager(){};


	public static ArrayList<User> getUsers()
	{
		return _dbut.getUsers();
	}
	
	public static void setUsersConnected(ArrayList<User> _usersConnected) {
		UserManager._usersConnected = _usersConnected;
	}

	public static ArrayList<User> getUsersConnected() {
		return _usersConnected;
	}

//	public static ArrayList<User> getUsersConnected(Integer id) {
//		ArrayList<User> users = new ArrayList<User>();
//		for (User user : _usersConnected) {
//			if (id.equals(user.getId())) {
//				users.add(user);
//			}
//		}
//		return users;
//	}

	public static User getUserConnected(String login) {
		for (User user : _usersConnected) {
			if (login.equals(user.getLogin())) {
				return user;
			}
		}
		return null;
	}
	
	public static User getUserConnected(Integer id) {
		for (User user : _usersConnected) {
			if (id.equals(user.getId())) {
				return user;
			}
		}
		return null;
	}
	
	public static void addUserConnected(User user) {
		System.out.println("User"+ user.getId() + "("+ user.getName() +") is connected");
		_usersConnected.add(user);
		System.err.println(_usersConnected);
		
		if (!user.getIsConnected()) {			
			user.setIsConnected(true);
			for (User contact : user.getContacts()) {
				for (User userConnected : _usersConnected) {
					if (userConnected.getId() == contact.getId()) {
						for (Websocket WS : userConnected.getWebsockets()) {
							WS.emit("updateContactStatus", user.getLogin(), user.getLastLoginDateFormated());
						}
					}
				}
			}
		}
	}
	
	public static void delUserConnected(User user) {
		System.out.println("User"+ user.getId() + "("+ user.getName() +") is disconnected");
		_usersConnected.remove(user);
		System.err.println(_usersConnected);
		user.setIsConnected(false);
		for (User contact : user.getContacts()) {
			for (User userConnected : _usersConnected) {
				if (userConnected.getId() == contact.getId()) {
					for (Websocket WS : userConnected.getWebsockets()) {
						WS.emit("updateContactStatus", user.getLogin(), user.getLastLoginDateFormated());
					}
				}
			}
		}
	}
	
	public static ArrayList<User> findContacts(int userId, String searchString) {
		return _dbut.findContacts(userId, searchString);
	}
	
	public static User getUser(int id) {
		return _dbut.getUser(id);
	}
	
	public static User getUser(String login) {
		return _dbut.getUser(login);
	}
	
	public static Boolean checkCredentials(String login, String password) {
		return _dbut.checkCredentials(login, password);
	}
	
	public static String userExists(String email, String phone, String login) {
		return _dbut.userExists(email,phone,login);
	}
	
	public static void addUser(String firstName,String lastName,String email,String phone,String login,String password) {
		_dbut.addUser(firstName,lastName,email,phone,login,password);
	}
	
}

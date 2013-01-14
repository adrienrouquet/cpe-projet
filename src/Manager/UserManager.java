package Manager;

import java.util.ArrayList;
import java.util.HashMap;

import AppCore.User;
import AppCore.Websocket;
import DB.DBUserToolbox;


/**
 * AppCore UserManager
 */
public abstract class UserManager {

	private static DBUserToolbox _dbut = new DBUserToolbox();
	private static HashMap<Integer, User> _usersConnected = new HashMap<Integer, User>();

	public UserManager(){};

	public static ArrayList<User> getUsers()
	{
		return _dbut.getUsers();
	}
	
	public static int getIdFromLogin(String login)
	{
		return _dbut.getIdFromLogin(login);
	}
	
	public static void setUsersConnected(HashMap<Integer, User> _usersConnected) {
		UserManager._usersConnected = _usersConnected;
	}

	public static HashMap<Integer, User> getUsersConnected() {
		return _usersConnected;
	}

	public static User getUserConnected(String login) {
		return _usersConnected.get(UserManager.getIdFromLogin(login));
	}
	
	public static User getUserConnected(Integer id) {
		return _usersConnected.get(id);
	}
	
	public static void addUserConnected(User user) {
		System.out.println("User"+ user.getId() + "("+ user.getName() +") is connected");
		_usersConnected.put(user.getId(),user);
		
		UserManager.updateContactsStatus(user);
	}
	
	public static void delUserConnected(User user) {
		System.out.println("User"+ user.getId() + "("+ user.getName() +") is disconnected");
		_usersConnected.remove(user.getId());
		
		UserManager.updateContactsStatus(user);
	}
	
	private static void updateContactsStatus (User user) {
		for (User contact : user.getContacts()) {
			User connectedContact = UserManager.getUserConnected(contact.getId());
			if(connectedContact != null)
			{
				for (Websocket WS : connectedContact.getWebsockets()) {
						WS.emit("updateContactStatus", user.getLogin(), user.getLastLoginDateFormated());
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

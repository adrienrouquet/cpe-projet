package Bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

import Class.Websocket;
import DB.DBUserToolbox;


/**
 * Class User
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5313606931922030655L;
	private int _id = 0;
	private String _login = "";
	private String _password = "";
	private String _email = "";
	private String _phone = "";
	private String _firstName = "";
	private String _lastName = "";
	private Timestamp _lastLoginDate = null;
	private boolean _isConnected = false;
	private ArrayList<Integer> _websockets = new ArrayList<Integer>();
	
	private DBUserToolbox _dbut = null;

	public User () { 	  
		_dbut 	= new DBUserToolbox();
	};
	
	public User (int id, String login, String password)
	{ 
		_id 		= id;
		_login 		= login;
		_password 	= password;
		_dbut 		= new DBUserToolbox();
	};
	
	public User (int id, String login, String email, String phone, String firstName, String lastName, Timestamp lastLoginDate)
	{ 
		_id 			= id;
		_login 			= login;
		_email	 		= email;
		_phone	 		= phone;
		_firstName 		= firstName;
		_lastName 		= lastName;
		_lastLoginDate  = lastLoginDate;
		_dbut 			= new DBUserToolbox();
	};

	public User (int id, String login, String password, String email, String phone, String firstName, String lastName, Timestamp lastLoginDate)
	{ 
		_id 			= id;
		_login 			= login;
		_password		= password;
		_email	 		= email;
		_phone	 		= phone;
		_firstName 		= firstName;
		_lastName 		= lastName;
		_lastLoginDate  = lastLoginDate;
		_dbut 			= new DBUserToolbox();
	};
	
	public void setId ( int id )
	{
		this._id = id;
	}
	public int getId ( ) {
		return this._id;
	}
	public void setLogin ( String login ) {
		this._login = login;
	}
	public String getLogin ( ) {
		return this._login;
	}
	public void setPassword ( String password ) {
		_login = password;
	}
	public String getEmail ( ) {
		return this._email;
	}
	public void setEmail ( String email ) {
		this._email = email;
	}
	public String getPhone() {
		return this._phone;
	}
	public String getFirstName() {
		return this._firstName;
	}

	public void setFirstName(String firstName) {
		this._firstName = firstName;
	}

	public String getLastName() {
		return this._lastName;
	}

	public void setLastName(String lastName) {
		this._lastName = lastName;
	}

	public Timestamp getLastLoginDate() {
		return this._lastLoginDate;
	}

	public void setLastLoginDate(Timestamp lastLoginDate) {
		this._lastLoginDate = lastLoginDate;
	}

	public void setPhone(String phone) {
		this._phone = phone;
	}
	public void setIsConnected ( boolean isConnected ) {
		_isConnected = isConnected;
	}
	public boolean getIsConnected ( ) {
		return _isConnected;
	}

	public ArrayList<Integer> getWebsockets() {
		return _websockets;
	}

	public void setWebsockets(ArrayList<Integer> websockets) {
		this._websockets = websockets;
	}
	
	public void addWebSocket(Integer websocketId) {
		_websockets.add(websocketId);
		System.out.println("ADD WS: " + _firstName + ": " + _websockets);
	}
	
	public void delWebsocket(Integer websocketId) {
		_websockets.remove(websocketId);
		System.out.println("DEL WS: " + _firstName + ": " + _websockets);
	}

}

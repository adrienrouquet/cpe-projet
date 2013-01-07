package Class;

import java.sql.Timestamp;
import java.util.ArrayList;

import Class.Websocket;
import DB.DBUserToolbox;
import Manager.MsgManager;


/**
 * Class User
 */
public class User{

	/**
	 * 
	 */
	private int _id = 0;
	private String _login = "";
	private String _password = "";
	private String _email = "";
	private String _phone = "";
	private String _firstName = "";
	private String _lastName = "";
	private Timestamp _lastLoginDate = null;
	private boolean _isConnected = false;
	private Websocket _websocket = null;
	private MsgManager _mm = null;
	
	private DBUserToolbox _dbut = null;

	public User () { 	  
		_dbut 	= new DBUserToolbox();
		_mm		= new MsgManager();
	};
	
	public User (int id, String login, String password)
	{ 
		_id 		= id;
		_login 		= login;
		_password 	= password;
		_dbut 		= new DBUserToolbox();
		_mm			= new MsgManager(_id);
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
		_mm				= new MsgManager(_id);
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
		_mm				= new MsgManager(_id);
	};
	
	public ArrayList<User> getContacts()
	{
		return _dbut.getContacts(_id);
	}

	public MsgManager getMsgManager()
	{
		return this._mm;
	}
	
	public void setId ( int id )
	{
		this._id = id;
		_mm.setSrcUserId(id);
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

	public Websocket getWebsocket() {
		return _websocket;
	}

	public void setWebsocket(Websocket websocket) {
		this._websocket = websocket;
	}
}

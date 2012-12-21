package Bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.*;

import Class.Msg;
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
	private String _login = "null";
	private String _password = "null";
	private boolean _isConnected = false;
	private DBUserToolbox _dbut = null;

	public User () { 	  
		_dbut 	= new DBUserToolbox();
	};

	public User (int id, String login, String password)
	{ 
		_id 		= id;
		_login 	= login;
		_password = password;
		_dbut 	= new DBUserToolbox();
	};

	public void setId ( int newVar )
	{
		_id = newVar;
	}
	public int getId ( ) {
		return _id;
	}
	public void setLogin ( String newVar ) {
		_login = newVar;
	}
	public String getLogin ( ) {
		return _login;
	}
	public void setIsConnected ( boolean newVar ) {
		_isConnected = newVar;
	}
	public boolean getIsConnected ( ) {
		return _isConnected;
	}

}

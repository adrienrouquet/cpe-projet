package DB;

import java.sql.ResultSet;

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

	
	public ResultSet getUsers()
	{
		return getResult("CALL getUsers()");
	}
	
	public ResultSet getName(Integer id)
	{
		return getResult("CALL getName(" + id + ")");
	}
	
	public ResultSet getLastLogin(Integer id)
	{
		return getResult("CALL getLastLogin(" + id + ")");
	}

}

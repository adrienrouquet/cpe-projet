package DB;

import java.sql.ResultSet;

import DB.DBHandler;


/**
 * Class DBUserToolbox
 */
public class DBMsgToolbox extends DBToolbox {
	
	public DBMsgToolbox ()
	{
		super();
		_dbName = "cpe-projet_db";
		_dbHandler = new DBHandler(_dbName);
	}
	
	public ResultSet getMessages(Integer userId)
	{
		return getResult("CALL getMessages('" + userId.toString() + "')");
	}

}

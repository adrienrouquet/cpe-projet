package DB;

import java.sql.ResultSet;

import DB.DBHandler;


/**
 * Class DBUserToolbox
 */
public class DBUserToolbox extends DBToolbox {

	private String _dbName = "";
	private DBHandler _dbHandler = null;
	
	public DBUserToolbox ()
	{
		super();
		_dbName = "cpe-projet_db";
		_dbHandler = new DBHandler(_dbName);
	}

	private ResultSet getResult(String query)
	{
		return _dbHandler.executeQueryRS(query);
	}

	private boolean executeQuery(String query)
	{
		return _dbHandler.executeQuery(query);
	}
	
	public ResultSet getUsers()
	{
		return getResult("CALL getUsers()");
	}
	
	public void closeConn()
	{
		_dbHandler.closeConn();
	}

}

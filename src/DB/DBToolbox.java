package DB;

import java.sql.ResultSet;


/**
 * Class DBToolbox
 */
abstract public class DBToolbox {

	protected String _dbName = "";
	protected DBHandler _dbHandler = null;
  
	public DBToolbox () { };

	protected ResultSet getResult(String query)
	{
		return _dbHandler.executeQueryRS(query);
	}

	protected boolean executeQuery(String query)
	{
		return _dbHandler.executeQuery(query);
	}

	public void closeConn()
	{
		_dbHandler.closeConn();
	}
}

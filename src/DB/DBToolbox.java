package DB;

import java.sql.Connection;
import java.sql.ResultSet;


/**
 * Class DBToolbox
 */
abstract public class DBToolbox {

	protected String _dbName = "";
	protected DBHandler _dbHandler = null;
  
	public DBToolbox () { };

	protected ResultSet executeQueryRS(Connection conn, String query)
	{
		return _dbHandler.executeQueryRS(conn, query);
	}

	protected boolean executeQuery(String query)
	{
		return _dbHandler.executeQuery(query);
	}
	
	protected Connection getConn()
	{
		return _dbHandler.getConn();
	}
	
	protected boolean closeConn(Connection conn)
	{
		return _dbHandler.closeConn(conn);
	}
	
}

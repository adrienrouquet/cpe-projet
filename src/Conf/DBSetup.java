package Conf;
import java.io.File;  
import java.io.FileReader;  
import java.io.IOException;

import java.sql.SQLException;  

import javax.servlet.ServletContext;

import DB.DBHandler;

public final class DBSetup {

	static String _dbName = "myam-db";	
	
	public static void resetDatabase(ServletContext context) throws SQLException  
	{  
		DBHandler _dbHandler = new DBHandler(_dbName);
		
		DBScriptRunner sr = new DBScriptRunner(_dbHandler.getConn(),false,false);
		
		
		try {			
			sr.runScript(new FileReader(new File(context.getRealPath("WEB-INF/sql/DBTables.sql"))));
			sr.runScript(new FileReader(new File(context.getRealPath("WEB-INF/sql/DBProcedures.sql"))));			
			sr.runScript(new FileReader(new File(context.getRealPath("WEB-INF/sql/DBDefaultEntries.sql"))));
		} catch (IOException e) {
			System.out.println("Error in Conf.DBSetup: " + e.getMessage());
		}
		
			
	}
}

package TestEnvSetup;
import java.io.BufferedReader;  

import java.io.File;  
import java.io.FileNotFoundException;
import java.io.FileReader;  
import java.io.IOException;

import java.sql.SQLException;  
import java.sql.DriverManager;  
import java.sql.Connection;  
import java.sql.Statement;  

public class DBReset {

	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";  

	static
	{  
		try  
		{  
			Class.forName(DRIVER_NAME).newInstance();  
			System.out.println("*** Driver loaded");  
		}  
		catch(Exception e)  
		{  
			System.out.println("*** Error DRV: "+e.toString());  
			System.out.println("*** ");  
			System.out.println("*** Error : ");  
			e.printStackTrace();  
		}  

	}  

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/cpe-projet_db";  
	private static final String USER = "root";  
	private static final String PASSWORD = "password";  

	public static Connection getConnection() throws SQLException  
	{  
		return DriverManager.getConnection(URL, USER, PASSWORD);  
	}  

	public static void resetDatabase() throws SQLException  
	{  
		ScriptRunner sr = new ScriptRunner(DBReset.getConnection(),false,false);
		
		try {
			sr.runScript(new FileReader(new File("./git/cpe-projet/src/TestEnvSetup/DBResetContent.sql")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sr.runScript(new FileReader(new File("./Documents/Git/cpe-projet/src/TestEnvSetup/DBResetContent.sql")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
}

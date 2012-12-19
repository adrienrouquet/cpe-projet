package Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import DB.DBMsgToolbox;
import DB.DBUserToolbox;


/**
 * Class UserManager
 */
public abstract class UserManager {

	private static DBUserToolbox _dbut = new DBUserToolbox();

	public UserManager(){};


	public static ResultSet getUsers()
	{
		return _dbut.getUsers();
	}

	public static String getName(Integer id)
	{

		ResultSet rs = _dbut.getName(id);
		if(rs != null)
		{
			try {
				rs.first();
				return rs.getString("name");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "N/A";
	}
	
	public static Timestamp getLastLogin(Integer id)
	{

		ResultSet rs = _dbut.getLastLogin(id);
		if(rs != null)
		{
			try {
				rs.first();
				return (Timestamp) rs.getObject("lastLoginDate");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}  	

}

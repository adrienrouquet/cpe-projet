package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Class.Msg;
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
	
	public ArrayList<Msg> getMessages(Integer userId)
	{
		Connection conn = getConn();
		ResultSet rs = executeQueryRS(conn, "CALL getMessages('" + userId.toString() + "')");
		ArrayList<Msg> messages = new ArrayList<Msg>();
		
		try {
			while(rs.next())
			{
				messages.add(new Msg(rs.getInt("id"),rs.getInt("srcUserId"),rs.getInt("dstUserId"),rs.getString("content"),rs.getTimestamp("sentDate"),rs.getBoolean("isDelivered")));
			}
		} catch (SQLException e) {
			System.out.println("Error in getMessages:" +e.getMessage());
		}
		closeConn(conn);
		return messages;
	}

}

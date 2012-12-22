package DB;

import java.sql.CallableStatement;
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
		CallableStatement cs = null;
		ArrayList<Msg> messages = null;
		ResultSet rs = null;
		
		try {
			cs = conn.prepareCall("{CALL getMessages(?)}");
			cs.setInt("pUserId", userId);			
			rs = cs.executeQuery();		
		
			messages = new ArrayList<Msg>();
		
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
	
	public Boolean sendMessage(int srcUserId, int dstUserId, String content)
	{
		Connection conn = getConn();
		CallableStatement cs = null;
		Boolean result = null;
		
		
		try {
			cs = conn.prepareCall("{CALL getMessages(?)}");
			cs.setInt("pSrcUserId", srcUserId);
			cs.setInt("pDstUserId", dstUserId);
			cs.setInt("pContentTypeId", 1);
			cs.setString("pContent", content);			
			result = cs.execute();
			
		} catch (SQLException e) {
			System.out.println("Error in sendMessage:" +e.getMessage());
		}
		
		closeConn(conn);
		
		return result;
	}

	

}

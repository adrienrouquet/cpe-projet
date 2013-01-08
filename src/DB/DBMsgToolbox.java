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
	
	public ArrayList<Msg> getMessages(int srcUserId, int dstUserId)
	{
		Connection conn 		= getConn();
		CallableStatement cs 	= null;
		ResultSet rs 			= null;
		ArrayList<Msg> messages = null;
		
		
		try {
			cs = conn.prepareCall("{CALL getMessages(?,?)}");
			cs.setInt("pSrcUserId", srcUserId);
			cs.setInt("pDstUserId", dstUserId);
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
	
	public Msg sendMessage(int srcUserId, int dstUserId, String content)
	{
		Connection conn 		= getConn();
		CallableStatement cs 	= null;
		ResultSet rs 			= null;
		Msg msg					= null;
		
		try {
			cs = conn.prepareCall("{CALL sendMessage(?,?,?,?)}");
			cs.setInt("pSrcUserId", srcUserId);
			cs.setInt("pDstUserId", dstUserId);
			cs.setInt("pContentTypeId", 1);
			cs.setString("pContent", content);			
			
			rs = cs.executeQuery();
			while(rs.next())
			{
				msg = new Msg(rs.getInt("id"),rs.getInt("srcUserId"),rs.getInt("dstUserId"),rs.getString("content"),rs.getTimestamp("sentDate"),rs.getBoolean("isDelivered"));
			}
			
		} catch (SQLException e) {
			System.out.println("Error in sendMessage:" +e.getMessage());
		}
		closeConn(conn);
		
		return msg;
	}
	
	public int getNonDeliveredMessageCount(int srcUserId, int dstUserId)
	{
		Connection conn = getConn();
		CallableStatement cs = null;
		ResultSet rs = null;
		
		
		try {
			cs = conn.prepareCall("{CALL getNonDeliveredMessageCount(?,?)}");
			cs.setInt("pSrcUserId", srcUserId);
			cs.setInt("pDstUserId", dstUserId);		
			
			rs = cs.executeQuery();
			while(rs.next())
			{
				return rs.getInt("count");
			}
			
		} catch (SQLException e) {
			System.out.println("Error in getNonDeliveredMessageCount:" +e.getMessage());
		}
		closeConn(conn);
		
		return 0;
	}
	
	public Boolean setMessageDelivered(int msgId)
	{
		Connection conn = getConn();
		CallableStatement cs = null;
		Boolean rs = false;
		
		
		try {
			cs = conn.prepareCall("{CALL setMessageDelivered(?)}");
			cs.setInt("pMsgId", msgId);			
			
			rs = cs.execute();
			
			
		} catch (SQLException e) {
			System.out.println("Error in setMessageDelivered:" +e.getMessage());
		}
		closeConn(conn);
		
		return rs;
	}

	

}

package Beans;

import java.sql.ResultSet;
import java.util.*;

import DB.DBMsgToolbox;
import DB.DBUserToolbox;

public class Msg {

	private String _text = "null";
	private int _senderId = 0;
	private int _receiverId = 0;
	private DBMsgToolbox _dbmt = null;

	public Msg () 
	{ 
		_dbmt = new DBMsgToolbox();  
	};
	
	private void setText ( String newVar ) {
		_text = newVar;
	}
	private String getText ( ) {
		return _text;
	}

	private void setSenderId ( int newVar ) {
		_senderId = newVar;
	}
	private int getSenderId ( ) {
		return _senderId;
	}

	private void setReceiverId ( int newVar ) {
		_receiverId = newVar;
	}
	private int getReceiverId ( ) {
		return _receiverId;
	}

	public ResultSet getMessages(Integer userId)
	{
		return _dbmt.getMessages(userId);
	}

}

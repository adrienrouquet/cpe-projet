package Manager;

import java.util.ArrayList;

import AppCore.Msg;
import DB.DBMsgToolbox;


public class MsgManager{


	private int _srcUserId 		= 0;
	private int _dstUserId 		= 0;
	
	private DBMsgToolbox _dbmt	= null;
	
	public MsgManager () {
		_dbmt 		= new DBMsgToolbox();
	};
	
	public MsgManager (int srcUserId) { 
		_srcUserId 	= srcUserId;
		_dbmt 		= new DBMsgToolbox();
	};
	

	public ArrayList<Msg> getMessages(int srcUserId, int dstUserId)
	{
		return _dbmt.getMessages(srcUserId, dstUserId);
	}

	public int getSrcUserId() {
		return this._srcUserId;
	}
	
	public void setSrcUserId(int srcUserId) {
		this._srcUserId = srcUserId;
	}
	
	public int getDstUserId() {
		return this._dstUserId;
	}
	
	public void setDstUserId(int dstUserId) {
		this._dstUserId = dstUserId;
	}
	
	public int sendMessage(String content)
	{
		return _dbmt.sendMessage(_srcUserId, _dstUserId, content);
	}
	
	public int getNonDeliveredMessageCount(int dstUserId)
	{
		return _dbmt.getNonDeliveredMessageCount(_srcUserId, dstUserId);
	}
	
	public Boolean setMessageDelivered(int msgId)
	{
		return _dbmt.setMessageDelivered(msgId);
	}

}

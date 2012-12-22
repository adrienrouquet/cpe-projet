package Bean;

import java.io.Serializable;
import java.util.ArrayList;

import Class.Msg;
import DB.DBMsgToolbox;


/**
 * Class MsgManager
 */
public class MsgManager implements Serializable{


	private static final long serialVersionUID = 7682107397924258436L;
	
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
	

	public ArrayList<Msg> getMessages(int id)
	{
		return _dbmt.getMessages(id);
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
	public void setDstUserId(String dstUserId) {
		if(dstUserId != null)
			this._dstUserId = Integer.parseInt(dstUserId);
	}
	public void setDstUserId(int dstUserId) {
		this._dstUserId = dstUserId;
	}
	public void sendMessage(String content)
	{
		_dbmt.sendMessage(_srcUserId, _dstUserId, content);
	}
	

}

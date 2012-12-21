package Manager;

import java.io.Serializable;
import java.sql.ResultSet;

import DB.DBMsgToolbox;


/**
 * Class MsgManager
 */
public class MsgManager implements Serializable{


	private static final long serialVersionUID = 7682107397924258436L;
	private DBMsgToolbox _dbmt = null;
	public MsgManager () { 
		_dbmt = new DBMsgToolbox();
	};

	public ResultSet getMessages(Integer userId)
	{
		return _dbmt.getMessages(userId);
	}

}

package Class;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import DB.DBMsgToolbox;

public class Msg {

	private int _id = 0;
	private int _srcUserId = 0;
	private int _dstUserId = 0;
	private String _content = "";
	private Timestamp _sentDate = null;
	private Boolean _isDelivered = false;
	private DBMsgToolbox _dbmt = null;
	
	public Msg () 
	{ 
		_dbmt = new DBMsgToolbox();
	};
	
	public Msg (int srcUserId, int dstUserId, String content, Timestamp sentDate, Boolean isDelivered)
	{ 
		setSrcUserId(srcUserId);
		setDstUserId(dstUserId);
		setContent(content);
		setSentDate(sentDate);
		setIsDelivered(isDelivered);
		
		_dbmt = new DBMsgToolbox();  
	};
	
	public Msg (int id, int srcUserId, int dstUserId, String content, Timestamp sentDate, Boolean isDelivered)
	{ 
		setId(id);
		setSrcUserId(srcUserId);
		setDstUserId(dstUserId);
		setContent(content);
		setSentDate(sentDate);
		setIsDelivered(isDelivered);
		_dbmt = new DBMsgToolbox();  
	};
	
	
	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public int getSrcUserId() {
		return _srcUserId;
	}

	public void setSrcUserId(int srcUserId) {
		this._srcUserId = srcUserId;
	}

	public int getDstUserId() {
		return _dstUserId;
	}

	public void setDstUserId(int dstUserId) {
		this._dstUserId = dstUserId;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		this._content = content;
	}

	public Timestamp getSentDate() {
		return _sentDate;
	}

	public void setSentDate(Timestamp sentDate) {
		this._sentDate = sentDate;
	}

	public Boolean isDelivered() {
		return _isDelivered;
	}

	public void setIsDelivered(Boolean isDelivered) {
		this._isDelivered = isDelivered;
	}

	public String getDateFormated() {
		return new SimpleDateFormat("MM/dd/yyyy \'at\' HH:mm").format(this._sentDate);
	}
	
	public String isDeliveredFormated() {
		return this._isDelivered?"//":"/";
	}
	
	public JSONObject getJsonMsg(String... keys) {
		JSONObject jsonMsg = new JSONObject();
		for (String key : keys) {
			switch (key) {
			case "id":
				jsonMsg.put("id", String.valueOf(this._id));
				break;
			case "content":
				jsonMsg.put("content", this._content);
				break;
			case "date":
				jsonMsg.put("date", this.getDateFormated());
				break;
			case "sender":
				jsonMsg.put("sender", Manager.UserManager.getName(_srcUserId));
				break;
			case "status":
				jsonMsg.put("status", this.isDeliveredFormated());
				break;
			default:
				break;
			}
		}
		
		return jsonMsg;
	}
	
	public String getJsonStringifyMsg(String... keys) {
		return getJsonMsg(keys).toJSONString();
	}
}

package Class;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import DB.DBMsgToolbox;
import Manager.UserManager;

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
		this._srcUserId = srcUserId;
		this._dstUserId = dstUserId;
		this._content = content;
		this._sentDate = sentDate;
		this._isDelivered = isDelivered;
		_dbmt = new DBMsgToolbox();  
	};
	
	public Msg (int id, int srcUserId, int dstUserId, String content, Timestamp sentDate, Boolean isDelivered)
	{ 
		this._id = id;
		this._srcUserId = srcUserId;
		this._dstUserId = dstUserId;
		this._content = content;
		this._sentDate = sentDate;
		this._isDelivered = isDelivered;
		_dbmt = new DBMsgToolbox();
	};
	
	public Msg (JSONObject json) {
		if (json.containsKey("id"))
			this._id = Integer.parseInt((String) json.get("id"));
		
		if (json.containsKey("content"))
			this._content = (String) json.get("content");
		
		if (json.containsKey("sentDate"))
			this._sentDate = new Timestamp((Long) json.get("sentDate"));
		
		if (json.containsKey("status")) {
			if (!(this.isDeliveredFormated().equals((String) json.get("status"))))
				this._isDelivered = true;
		}
		_dbmt = new DBMsgToolbox();
	}
	
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
		
		User user = UserManager.getUserConnected(this._srcUserId);
		if (user != null) {
			ArrayList<Websocket> websockets = user.getWebsockets();
			if (websockets != null && websockets.size() > 0) {
				for (Websocket WS : user.getWebsockets()) {
					WS.emit("updateMessageStatus" ,this.getJsonStringifyMsg("id", "status"));
				}
			}
		}
		_dbmt.setMessageDelivered(this._id);
	}

	public String getDateFormated() {
		return new SimpleDateFormat("MM/dd/yyyy \'at\' HH:mm").format(this._sentDate);
	}
	
	public String isDeliveredFormated() {
		String cl = this._isDelivered?"messageStatusReceived":"messageStatusSent";
		
		String statusHtml = "<div class='"
				+ cl
				+ "'></div>";
		return statusHtml;
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
			case "sentDate":
				jsonMsg.put("sentDate", this.getSentDate().getTime());
				break;
			case "srcLogin":
				jsonMsg.put("srcLogin", User.getLogin(_srcUserId));
				break;
			case "srcName":
				jsonMsg.put("srcName", User.getName(_srcUserId));
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

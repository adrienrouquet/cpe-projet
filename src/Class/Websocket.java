package Class;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Map;
import java.util.HashMap;

import org.apache.catalina.websocket.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
//import org.json.simple.JSONArray;

import Manager.UserManager;

public class Websocket extends MessageInbound{

	private Bean.MsgManager _msgManager = null;
	private Integer _id = null;

	public Websocket(Integer id, Bean.MsgManager msgManager) {
		_id = id;
		_msgManager = msgManager;
	}
	
	public Integer getId() {
		return _id;
	}
	public void setId(Integer id) {
		this._id = id;
	}
	
	@Override
	protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
		throw new UnsupportedOperationException("Binary not supported");
	}

	@Override
	protected void onTextMessage(CharBuffer data) throws IOException {
		System.out.println("onTextMessage");
		
		String msg = data.toString();
		System.out.println(msg);

		JSONObject json = (JSONObject) JSONValue.parse(msg);
		
//		String receiver = (String) json.get("receiver");
//		json.put("sender", nickname);
//		json.remove("receiver");
		String jsonText = json.toJSONString();
		System.out.println(jsonText);
		System.out.println(_msgManager.getDstUserId());

//		_msgManager.sendMessage();
		
		try {
			WsOutbound conn = this.getWsOutbound();
			conn.writeTextMessage(CharBuffer.wrap(jsonText));
		} catch (Exception e) {
			System.err.println("ERROR: user is probably disconnected: " + e.getMessage());
			// send msg to DB
		}
	}

	
	@Override
	protected void onOpen(WsOutbound outbound) {
		System.out.println("onOpen: " + this.getWsOutbound());
		System.out.println("msgDst: " + _msgManager.getDstUserId());
//		connectionsMap.put(nickname, outbound);
//		String message = String.format("{ \"message\": \"" + nickname + " has joined\" }");
//		broadcast(message);
	}
	
	@Override
	protected void onClose(int status) {
		System.out.println("onClose: " + this.getWsOutbound());
		Manager.WebsocketManager.delWebsocket(this);
		
//		String message = String.format("{ \"message\": \"" + nickname + " is disconnected\" }");
//		connectionsMap.remove(nickname);
//		broadcast(message);
	}

//	private void broadcast(String msg) {
//		System.out.println("broadcast");
//		for (WsOutbound connection : connectionsMap.values()) {
//			try {
//				connection.writeTextMessage(CharBuffer.wrap(msg));
//			} catch (IOException ignore) {
//				//Ignore
//			}
//		}
//	}
	
}

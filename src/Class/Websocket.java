package Class;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import org.apache.catalina.websocket.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import Manager.WebsocketManager;
//import org.json.simple.JSONArray;

public class Websocket extends MessageInbound{

	private Bean.MsgManager _msgManager = null;

	public Websocket(Bean.MsgManager msgManager) {
		_msgManager = msgManager;
	}
	
	public Bean.MsgManager getMsgManager() {
		return _msgManager;
	}

	public void setMsgManager(Bean.MsgManager msgManager) {
		this._msgManager = msgManager;
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
		
		String jsonText = json.toJSONString();
		System.out.println(jsonText);

//		_msgManager.sendMessage((String) json.get("message"));
		
		try {
			System.out.println(_msgManager);
			Websocket WS = WebsocketManager.getWebsocket(_msgManager.getDstUserId());
			WsOutbound conn = WS.getWsOutbound();
			conn.writeTextMessage(CharBuffer.wrap(jsonText));
		} catch (Exception e) {
			System.err.println("ERROR: user is probably disconnected: " + e.getMessage());
		}
	}

	
	@Override
	protected void onOpen(WsOutbound outbound) {
		System.out.println("onOpen: " + this.getWsOutbound());
	}
	
	@Override
	protected void onClose(int status) {
		System.out.println("onClose: " + this.getWsOutbound());
		Manager.WebsocketManager.delWebsocket(this);
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

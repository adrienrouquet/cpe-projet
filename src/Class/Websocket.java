package Class;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;

import org.apache.catalina.websocket.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;

import Bean.User;
import Manager.UserManager;
import Manager.WebsocketManager;

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
	protected void onTextMessage(CharBuffer buffer) throws IOException {
		System.out.println("User" + _msgManager.getSrcUserId() + ": Entering WebSocket.onTextMessage");
		
		String msg = buffer.toString();
		
		JSONObject jsonEvent = (JSONObject) JSONValue.parse(msg);
		
		System.out.println(jsonEvent);
		
		String event = (String) jsonEvent.get("event");
		JSONArray data = (JSONArray)  jsonEvent.get("data");
		
		switch (event) {
		case "sendMessage":
			JSONObject jsonSendMessage = (JSONObject) JSONValue.parse((String) data.get(0));
			onSendMessage(jsonSendMessage);
			break;
		case "updateMessageStatus":
			JSONObject jsonUpdateMessage = (JSONObject) JSONValue.parse((String) data.get(0));
			onUpdateMessageStatus(jsonUpdateMessage);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onOpen(WsOutbound outbound) {
		System.out.println("User" + _msgManager.getSrcUserId() + " ("+ UserManager.getName(_msgManager.getSrcUserId()) +"): Entering onOpen: " + this.getWsOutbound());
	}
	
	@Override
	protected void onClose(int status) {
		System.out.println("User" + _msgManager.getSrcUserId() + ": Entering onClose: " + this.getWsOutbound());
		for (User userBean : UserManager.getConnectedUser(_msgManager.getSrcUserId())) {
			if (this.equals(userBean.getWebsocket())) {
				UserManager.delUserConnected(userBean);
			}
		}
//		Manager.UserManager.delUserConnected(Manager.UserManager.getConnectedUser(_msgManager.getSrcUserId()));
	}

	public void onUpdateMessageStatus(JSONObject data) {
		System.err.println("EVENT: updateMessageStatus");

		// Simple verification: si on est ici, le status est forcement "/"
		if ("/".equals(data.get("status"))) {
			// MAJ en DB
			_msgManager.setMessageDelivered(Integer.parseInt((String) data.get("id")));
			// Le dst recoit le msg et update le status sur l'emetteur
			data.put("status", "//");
			for (User user : UserManager.getConnectedUser(_msgManager.getDstUserId())) {
				user.getWebsocket().emit("updateMessageStatus", data.toJSONString());
			}
		}
	}

	private void onSendMessage(JSONObject data) {
		System.err.println("EVENT: sendMessage");
		//On ajoute le nouveau message en DB et on recupere l'ID de l'insertion
		Integer msgId = _msgManager.sendMessage((String) data.get("content"));			
		//On ajoute l'ID dans le jsonDst pour le destinataire
		data.put("id",  msgId.toString());
		data.put("sender", Manager.UserManager.getName(_msgManager.getSrcUserId()));
		data.put("status", "/");
		//On publie un SSE qui publie une notification au user destinataire
		this.emit("updateMessageStatus", data.toJSONString());
		
		ArrayList<User> userBeans = UserManager.getConnectedUser(_msgManager.getDstUserId());
		if (userBeans.size() > 0) {			
			for (User user : userBeans)
				user.getWebsocket().emit("newMessage", data.toJSONString());
		} else {
			System.err.println("USER" + _msgManager.getDstUserId() + " NOT CONNECTED");			
		}
		
//		SSE.setNewMessageReceived(_msgManager.getSrcUserId(), _msgManager.getDstUserId());
//		System.out.println("User" + _msgManager.getSrcUserId() + ": added message to DB");
	}
	
	public void emit(String event, String... datas) {
		System.err.println("emit");
		JSONArray jsonArray = new JSONArray();
		for (String data : datas) {			
			jsonArray.add(data);
		}
		
		JSONObject jsonEvent = new JSONObject();
		jsonEvent.put("event", event);
		jsonEvent.put("data", jsonArray);
		
		System.out.println("jsonEvent");
		System.out.println(jsonEvent);
		try {
			WsOutbound conn = this.getWsOutbound();
			conn.writeTextMessage(CharBuffer.wrap(jsonEvent.toJSONString()));
		} catch (IOException e) {
			System.err.println("ERROR IN emit");
//			e.printStackTrace();
		}
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

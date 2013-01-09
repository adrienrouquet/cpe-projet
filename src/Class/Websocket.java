package Class;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;

import org.apache.catalina.websocket.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;

import Class.User;
import Manager.MsgManager;
import Manager.UserManager;

public class Websocket extends MessageInbound{

	private MsgManager _msgManager = null;

	public Websocket(MsgManager msgManager) {
		_msgManager = msgManager;
	}
	
	public MsgManager getMsgManager() {
		return _msgManager;
	}

	public void setMsgManager(MsgManager msgManager) {
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
		
		// On ne recoit que des messages JSON de la forme { "event": ... "data": [...] }
		JSONObject jsonEvent = (JSONObject) JSONValue.parse(msg);
		
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
		System.out.println("User" + _msgManager.getSrcUserId() + " ("+ User.getName(_msgManager.getSrcUserId()) +"): Entering onOpen: " + this.getWsOutbound());
	}
	
	@Override
	protected void onClose(int status) {
		System.out.println("User" + _msgManager.getSrcUserId() + ": Entering onClose: " + this.getWsOutbound());
		// Lorsque la websocket se ferme, on supprime l'user de la liste des users connectés
		for (User user : UserManager.getUsersConnected(_msgManager.getSrcUserId())) {
			if (this.equals(user.getWebsocket())) {
				UserManager.delUserConnected(user);
			}
		}
	}

	public void onUpdateMessageStatus(JSONObject data) {
		System.err.println("EVENT: updateMessageStatus");

		// MAJ en DB
//		System.out.println(data.get("id").getClass());
		_msgManager.setMessageDelivered(Integer.parseInt((String) data.get("id")));
		// Le dst recoit le msg et update le status sur l'emetteur
		data.put("status", "messageStatusReceived");
//		data.remove("content");
		for (User user : UserManager.getUsersConnected(_msgManager.getDstUserId())) {
			user.getWebsocket().emit("updateMessageStatus", data.toJSONString());
		}
	}

	private void onSendMessage(JSONObject data) {
		System.err.println("EVENT: sendMessage");
		// On ajoute le nouveau message en DB et on recupere le msg
		Msg msg = _msgManager.sendMessage((String) data.get("content"));
		
		// On cree un nouveau json avec l'id, le status et le timestamp
		JSONObject jsonUpMsg = msg.getJsonMsg("id", "status");
		jsonUpMsg.put("tmp", data.get("tmp"));
		
		// On envoit un event pour updater le status du message
		this.emit("updateMessageStatus", jsonUpMsg.toJSONString());
		
		// On ajoute le content et on enleve tmp
//		data.remove("tmp");
//		data.put("content", content);
		
		// Pour tous les users qui on le meme id (un utilisateur qui est connecté sur plusieurs interfaces), on envoit un event
		ArrayList<User> users = UserManager.getUsersConnected(_msgManager.getDstUserId());
		if (users.size() > 0) {
			for (User user : users)
				if (user.getMsgManager().getDstUserId() == _msgManager.getSrcUserId()) {
					user.getWebsocket().emit("newMessage", msg.getJsonStringifyMsg("id", "content", "date"));
				} else {
//					data.put("sender", UserManager.getName(_msgManager.getSrcUserId()));
					user.getWebsocket().emit("messageNotification", msg.getJsonStringifyMsg("src", "sender"));
				}
		} else {
			System.err.println("USER" + _msgManager.getDstUserId() + " NOT CONNECTED");
		}
	}
	
	public void emit(String event, String... datas) {
		System.err.println("emit: " + event);
		JSONArray jsonArray = new JSONArray();
		for (String data : datas) {
			jsonArray.add(data);
		}
		// On met en forme les données pour les envoyer: { "event": ..., "data": [...] }
		JSONObject jsonEvent = new JSONObject();
		jsonEvent.put("event", event);
		jsonEvent.put("data", jsonArray);
		
		System.err.println(jsonEvent.toJSONString());
		
		try {
			WsOutbound conn = this.getWsOutbound();
			System.err.println(conn);
			if (conn != null)
			conn.writeTextMessage(CharBuffer.wrap(jsonEvent.toJSONString()));
		} catch (IOException e) {
			System.err.println("ERROR IN emit");
//			e.printStackTrace();
		}
	}
}

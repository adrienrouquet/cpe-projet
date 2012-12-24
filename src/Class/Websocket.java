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
		System.out.println("User" + _msgManager.getSrcUserId() + ": Entering WebSocket.onTextMessage");
		
		String msg 			= data.toString();
		Integer msgId 		= 0;
		JSONObject jsonSrc 	= (JSONObject) JSONValue.parse(msg);
		JSONObject jsonDst 	= (JSONObject) jsonSrc.clone();		
		JSONObject jsonBack	= (JSONObject) jsonSrc.clone();
		
		System.out.println("Server: Received msg from User" + _msgManager.getSrcUserId() + " for User" + _msgManager.getDstUserId() + ":" + jsonSrc.toJSONString());

		
		//Si le message est un accuse de reception
		if(!jsonSrc.get("deliveredMsgId").equals("null")) 
		{
			_msgManager.setMessageDelivered(Integer.parseInt((String)jsonSrc.get("deliveredMsgId")));
			
		}
		//Si le message est un nouveau message
		else
		{			
			msgId = _msgManager.sendMessage((String) jsonSrc.get("content"));			
			jsonDst.put("newMsgId",  msgId.toString());
			System.out.println("User" + _msgManager.getSrcUserId() + ": added message to DB");
			
		}
		
		
		Websocket WS = WebsocketManager.getWebsocket(_msgManager.getDstUserId());
		if(WS != null)
		{
			WsOutbound conn = WS.getWsOutbound();
			
			conn.writeTextMessage(CharBuffer.wrap(jsonDst.toJSONString()));
			System.out.println("User" + _msgManager.getSrcUserId() + ": JSON msg sent to User" + _msgManager.getDstUserId() + ": " + jsonDst.toJSONString());
		}
		//L'utilisateur distant n'est pas connecte
		else
		{
			System.out.println("User " + _msgManager.getSrcUserId() + " : Warning: dstUser "  + _msgManager.getDstUserId() + " is disconnected ");

			
			
		}
		
		//Dans tous les cas, on donne l'ID du message au sender.
		jsonBack.put("sentMsgId", msgId);
		WS = WebsocketManager.getWebsocket(_msgManager.getSrcUserId());
		WsOutbound conn = WS.getWsOutbound();
		conn.writeTextMessage(CharBuffer.wrap(jsonBack.toJSONString()));
		System.out.println("User" + _msgManager.getSrcUserId() + ": JSON msg sent to himself: " + jsonBack.toJSONString());
		
	}

	
	@Override
	protected void onOpen(WsOutbound outbound) {
		System.out.println("User" + _msgManager.getSrcUserId() + ": Entering onOpen: " + this.getWsOutbound());
	}
	
	@Override
	protected void onClose(int status) {
		System.out.println("User" + _msgManager.getSrcUserId() + ": Entering onClose: " + this.getWsOutbound());
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

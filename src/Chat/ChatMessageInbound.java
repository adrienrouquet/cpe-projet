package Chat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Map;
import java.util.HashMap;

import org.apache.catalina.websocket.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
//import org.json.simple.JSONArray;

public class ChatMessageInbound extends MessageInbound{

	private static Map<String, WsOutbound> connectionsMap = new HashMap<String, WsOutbound>();
	
	private final String nickname;
	

	public ChatMessageInbound(int id) {
		this.nickname = "GUEST_" + id;
	}
	
	@Override
	protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
		throw new UnsupportedOperationException("Binary not supported");
	}

	@Override
	protected void onTextMessage(CharBuffer message) throws IOException {
		System.out.println("onTextMessage");
		String msg = "(" + nickname + ")" + message.toString();
		broadcast(msg);
		
		msg = message.toString();
		System.out.println(msg);

		JSONObject json = (JSONObject) JSONValue.parse(message.toString());
		System.out.println(json);
		
		String dest = (String) json.get("dest");
		System.out.println(dest);
		WsOutbound conn = connectionsMap.get(dest);
		try {
			conn.writeTextMessage(CharBuffer.wrap((String)json.get("message")));
		} catch (IOException ignore) {
			//Ignore
		}
		
		System.out.println(connectionsMap);
	}

	private void broadcast(String msg) {
		System.out.println("broadcast");
		for (WsOutbound connection : connectionsMap.values()) {
			try {
				connection.writeTextMessage(CharBuffer.wrap(msg));
			} catch (IOException ignore) {
				//Ignore
			}
		}
	}
	
	@Override
	protected void onOpen(WsOutbound outbound) {
		System.out.println("onOpen");
		connectionsMap.put(nickname, outbound);
		System.out.println(connectionsMap);
		String message = String.format("** %s %s",nickname, "has joined **");
		broadcast(message);
	}
	
	@Override
	protected void onClose(int status) {
		System.out.println("onClose");
		String message = String.format("** %s %s",nickname, "has disconnected **");
		broadcast(message);
	}
}

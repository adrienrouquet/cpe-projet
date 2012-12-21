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

public class Websocket extends MessageInbound{

	private static Map<String, WsOutbound> connectionsMap = new HashMap<String, WsOutbound>();
	
	private final String nickname;
	

	public Websocket(int id) {
		this.nickname = "GUEST_" + id;
	}

	@Override
	protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
		throw new UnsupportedOperationException("Binary not supported");
	}

	@Override
	protected void onTextMessage(CharBuffer data) throws IOException {
		System.out.println("onTextMessage");
//		String msg = "(" + nickname + ")" + message.toString();
//		broadcast(msg);
		
		String msg = data.toString();
		System.out.println(msg);

		JSONObject json = (JSONObject) JSONValue.parse(data.toString());
		
		String receiver = (String) json.get("receiver");
		json.put("sender", nickname);
		json.remove("receiver");
		MsgManager.Send()
		String jsonText = json.toJSONString();
		System.out.print(jsonText);
		
		try {
			WsOutbound conn = connectionsMap.get(receiver);
			conn.writeTextMessage(CharBuffer.wrap(jsonText));
		} catch (Exception e) {
			System.err.println("ERROR: user is probably disconnected: " + e.getMessage());
			// send msg to DB
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
		String message = String.format("{ \"message\": \"" + nickname + " has joined\" }");
		broadcast(message);
	}
	
	@Override
	protected void onClose(int status) {
		System.out.println("onClose");
		String message = String.format("{ \"message\": \"" + nickname + " is disconnected\" }");
		connectionsMap.remove(nickname);
		broadcast(message);
	}
}

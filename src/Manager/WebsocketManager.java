package Manager;

import java.util.ArrayList;
import Class.Websocket;

public class WebsocketManager {
	
	private static ArrayList<Websocket> _websockets = new ArrayList<Websocket>();
	
//	private Integer _id = 0;

	public ArrayList<Websocket> getWebsockets() {
		return _websockets;
	}

	public void setWebsockets(ArrayList<Websocket> websockets) {
		_websockets = websockets;
	}
	
	public Websocket addWebsocket(Bean.MsgManager msgManager) {
		
		Websocket websocket = new Websocket(msgManager);
		_websockets.add(websocket);
		System.out.println("User" + websocket.getMsgManager().getSrcUserId() + ": webSocket Added");
		return websocket;
	}
	
	public static void delWebsocket(Websocket websocket) {
//		Integer userId = websocket.getMsgManager().getSrcUserId();
		// TODO FOREACH user.id == userId, user.websocket = null;
		_websockets.remove(websocket);
		System.out.println("User" + websocket.getMsgManager().getSrcUserId() + ": webSocket Deleted");
	}
	
	public static Websocket getWebsocket(Integer userId) {
		
		for (Websocket websocket: _websockets) {
			System.out.println("User" + websocket.getMsgManager().getSrcUserId() + ": Entering getWebSocket: DstUserId: " + websocket.getMsgManager().getDstUserId());
			if (websocket.getMsgManager().getSrcUserId() == userId)
				return websocket;
		}
		
		return null;
	}
}

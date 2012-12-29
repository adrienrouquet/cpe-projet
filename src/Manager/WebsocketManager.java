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
	
	public static Websocket getWebsocket(Integer dstUserId) {
		
		for (Websocket websocket: _websockets) {
			System.out.println("Browsing webSockets to get User" + dstUserId +"'s webSocket");
			if (websocket.getMsgManager().getSrcUserId() == dstUserId && websocket.getMsgManager().getDstUserId() != 0)
			{
				System.out.println("Found User" + dstUserId +"'s webSocket: interacting with User" + websocket.getMsgManager().getDstUserId());
				return websocket;
			}
		}
		
		return null;
	}
}

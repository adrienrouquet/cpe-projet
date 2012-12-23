package Manager;

import java.util.HashMap;

import Bean.User;
import Class.Websocket;

public class WebsocketManager {
	
	private static HashMap<Websocket, Bean.User> _websocketMap = new HashMap<Websocket, Bean.User>();
	
	private Integer _id = 0;

	public HashMap<Websocket, User> getWebsocketMap() {
		return _websocketMap;
	}

	public void setWebsocketMap(HashMap<Websocket, User> websocketMap) {
		_websocketMap = websocketMap;
	}
	
	public Websocket addWebsocket(Bean.User user, Bean.MsgManager msgManager) {
		
		Integer id = ++_id;
		Websocket websocket = new Websocket(id, msgManager);
		_websocketMap.put(websocket, user);
		
		return websocket;
	}
	
	public static void delWebsocket(Websocket websocket) {
		User user = _websocketMap.get(websocket);
		user.delWebsocket(websocket.getId());
		_websocketMap.remove(websocket);
	}
	
//	public static Websocket getWebsocket(Bean.User user) {
//		return _websocketMap.get(user);
//	}
}

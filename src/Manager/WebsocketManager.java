package Manager;

import Class.Websocket;

public abstract class WebsocketManager {
	
	public static Websocket getWebsocket(Integer userId) {
		Websocket WS = null;
		try {
			WS = Manager.UserManager.getConnectedUser(userId).getWebsocket();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		return WS;
	}
}

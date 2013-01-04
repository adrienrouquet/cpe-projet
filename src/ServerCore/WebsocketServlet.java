package ServerCore;

import org.apache.catalina.websocket.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Class.Websocket;
import Manager.UserManager;

/**
 * Servlet implementation class Test
 */
@WebServlet("/WebsocketServlet")
public class WebsocketServlet extends WebSocketServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     */
    public WebsocketServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected StreamInbound createWebSocketInbound(String arg0, HttpServletRequest req) {
		HttpSession session = req.getSession(true);
		Bean.User user = (Bean.User) session.getAttribute("userBean");
		System.out.println("User" + user.getId() + ": Entering createWebSocketInbound");
		
		Bean.MsgManager msgManager = (Bean.MsgManager) session.getAttribute("msgManagerBean");
		
		Websocket websocket = new Websocket(msgManager);
		user.setWebsocket(websocket);
		// A DEPLACER !!!
		UserManager.addUserConnected(user);
		System.err.println(UserManager.getUsersConnected());
		
		return websocket;
	}
	
}

package ServerCore;

import org.apache.catalina.websocket.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Bean.UserBean;
import Class.Websocket;
import Class.User;

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
		UserBean userBean = (Bean.UserBean) session.getAttribute("userBean");
		User user = userBean.getUser();
		
		System.out.println("User" + userBean.getId() + ": Entering createWebSocketInbound");
		
		Websocket websocket = new Websocket(userBean.getMsgManager());
		user.addWebsocket(websocket);
		
		return websocket;
	}
	
}

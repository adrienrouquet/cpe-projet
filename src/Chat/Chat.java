package Chat;

import org.apache.catalina.websocket.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Chat")
public class Chat extends WebSocketServlet {
	private static final long serialVersionUID = 1L;
       
    private int _id = 0;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Chat() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected StreamInbound createWebSocketInbound(String arg0,
			HttpServletRequest arg1) {
		
		System.out.println("createWebSocketInbound");
		return new ChatMessageInbound(++_id);
	}
	
}

package ServerCore;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.*;

@WebServlet("/AppServlet")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ChatRouter _router = null;
	
    public ChatServlet() {
        super();
        
    }
    
    private void router(HttpServletRequest req, HttpServletResponse res)
    {
    	String action = "view";
    	HttpSession session = req.getSession(true);
    	
    	ChatRouter cr = (ChatRouter) session.getAttribute("chatRouterBean");
    	if (req.getParameter("action") != null)
    	{
	    	if( req.getParameter("action").equals("openChat") )
	    	{
	    		action = "view";
	    		
	    		cr.setUrl("content/chat/chatWindow.jsp");
	    	}
	    	else
	    	{
	    		action = "view";
	    		cr.setUrl("content/chat/contactWindow.jsp");
	    	}
    	}
    	else
    	{
    		action = "view";
    		cr.setUrl("content/chat/contactWindow.jsp");    		
    	}
    	
    	RequestDispatcher rd = req.getRequestDispatcher(cr.getUrl());
		try {
			rd.forward(req, res);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("AppServlet: Entering doGet");
		router(req, res);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("AppServlet: Entering doPost");
		router(req, res);
	}
	
	
}

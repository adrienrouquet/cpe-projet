package ServerCore;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.MsgManager;


@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChatServlet() {
        super();
        
    }
    
    private void chatRouting(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
    	HttpSession session 	= req.getSession(true);
    	RequestDispatcher rd 	= null;
    	    	
    	//On recupere le userBean de la session    
    	Bean.User user 			= (Bean.User) session.getAttribute("userBean");		
		if(user == null)
		{
			System.out.println("Warning: userBean is null in ChatServlet");
			user	=  new Bean.User();
			session.setAttribute("userBean", user);
		}
    	
    	//On recupere le chatRouterBean de la session    	
    	Bean.ChatRouter cr 		= (Bean.ChatRouter) session.getAttribute("chatRouterBean");
    	if(cr == null)
    	{
    		System.out.println("Warning: chatRouterBean is null in ChatServlet");
    		cr = new Bean.ChatRouter();
    		session.setAttribute("chatRouterBean", cr);
    	}
    	cr.setAction(req.getParameter("action"));
    	
    	//On recupere le msgManagerBean de la session    	
    	Bean.MsgManager mm 		= (Bean.MsgManager) session.getAttribute("msgManagerBean");
    	if(mm == null)
    	{
    		System.out.println("Warning: msgManagerBean is null in ChatServlet");
    		mm = new Bean.MsgManager(user.getId());
    		session.setAttribute("msgManagerBean", mm);
    	}
    	mm.setDstUserId(req.getParameter("contactId"));
    	
		switch(cr.getAction())
		{
			case "openChat":
			{    		
				cr.setUrl("chatWindow.jsp");
	    		rd = req.getRequestDispatcher("content/chat/chat.jsp");
//	    		rd.forward(req, res);
	    	}break;
	    	//Action: on est sur la chatWindow et on veut retourner a la contactWindow. Il faut donc vider le dstUserId du msgManager
			case "backToContactView":
			{    		
				cr.setAction("DefaultView");
				cr.setUrl("contactWindow.jsp");
				mm.setDstUserId(0);
				System.out.println("test");
	    		rd = req.getRequestDispatcher("content/chat/chat.jsp");
//	    		rd.forward(req, res);
	    	}break;
	    	
	    	default:
	    	{
	    		cr.setAction("DefaultView");
	    		cr.setUrl("contactWindow.jsp");
	    		rd = req.getRequestDispatcher("content/chat/chat.jsp");
//	    		rd.forward(req, res);
	    	}break;
		}
		
    	rd.forward(req, res);	
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("User" + ((Bean.User)req.getSession(true).getAttribute("userBean")).getId() + ": Entering ChatServlet.doGet");
		chatRouting(req, res);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("ChatServlet: Entering doPost");
		chatRouting(req, res);
	}
	
	
}

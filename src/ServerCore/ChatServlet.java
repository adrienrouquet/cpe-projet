package ServerCore;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Class.User;
import Class.Websocket;
import Manager.UserManager;


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
    	Bean.UserBean userBean	= (Bean.UserBean) session.getAttribute("userBean");		
		if(userBean == null)
		{
			System.out.println("Warning: userBean is null in ChatServlet");
			res.sendRedirect("AccountServlet");
			return;
		}
    	
		//On recupere le searchBean de la session    
    	Bean.UserBean searchUserBean = (Bean.UserBean) session.getAttribute("searchUserBean");
		
		//On recupere le chatRouterBean de la session    	
    	Bean.Router cr 		= (Bean.Router) session.getAttribute("chatRouterBean");
    	if(cr == null)
    	{
    		System.out.println("Warning: chatRouterBean is null in ChatServlet");
    		cr = new Bean.Router();
    		session.setAttribute("chatRouterBean", cr);
    	}
    	cr.setAction(req.getParameter("action"));
    	
    	//On recupere le msgManagerBean de la session    	
    	Integer contactId = (req.getParameter("contactId") != null)?Integer.parseInt(req.getParameter("contactId")):null;
    	
		switch(cr.getAction())
		{
			case "openChat":
			{
				userBean.getMsgManager().setDstUserId(contactId);
				cr.setUrl("chatWindow.jsp");
	    		rd = req.getRequestDispatcher("content/chat/chat.jsp");
//	    		rd.forward(req, res);
	    	}break;
			
			case "deleteContact":
			{
				userBean.getUser().deleteContact(contactId);
				
				cr.setUrl("contactWindow.jsp");
	    		rd = req.getRequestDispatcher("content/chat/chat.jsp");
//	    		rd.forward(req, res);
			}break;
	    	
			case "openAddContactWindow":
			{ 
				//Chaque fois qu'on ouvre la page de recherche, le searchUserBean doit etre vide
				if(searchUserBean != null)
				{
					searchUserBean	=  new Bean.UserBean();
					session.setAttribute("searchUserBean", searchUserBean);
				}
				
				cr.setUrl("addContactWindow.jsp");
	    		rd = req.getRequestDispatcher("content/chat/chat.jsp");		

	    	}break;
	    	
			case "clearAddContactWindow":
			{   
				searchUserBean	=  new Bean.UserBean();
				session.setAttribute("searchUserBean", searchUserBean);
				cr.setUrl("addContactWindow.jsp");
	    		rd = req.getRequestDispatcher("content/chat/chat.jsp");		

	    	}break;
	    	
			case "findContact":
			{    		
				// Le searchUserBean est sense etre null avant la recherche
				if (searchUserBean != null)
				{
					searchUserBean		=  new Bean.UserBean();				
				}
				String searchString 		= req.getParameter("searchString").trim();
				
				searchUserBean.setLogin(searchString);
				
				session.setAttribute("searchUserBean", searchUserBean);
				cr.setUrl("addContactWindow.jsp");
	    		rd = req.getRequestDispatcher("content/chat/chat.jsp");
//	    		rd.forward(req, res);
	    		
	    	}break;
	    	
			case "addContact":
			{
				session.setAttribute("searchUserBean", null);
				userBean.getUser().addContact(contactId);
				
				User user = UserManager.getUserConnected(contactId);
				if (user != null) {
					for(Websocket WS : user.getWebsockets()) {
						WS.emit("contactRequestNotification");
					}
				}
					
					cr.setUrl("contactWindow.jsp");
					rd = req.getRequestDispatcher("content/chat/chat.jsp");
	    	}break;
	    	
			case "openContactRequestsWindow":
			{
				cr.setUrl("contactRequestsWindow.jsp");
	    		rd = req.getRequestDispatcher("content/chat/chat.jsp");
//	    		rd.forward(req, res);
			}
	    	
			case "submitContactRequests":
			{
				String answer = req.getParameter("acceptRequest");
				
				if ((answer != null) && (answer != "")) 
				{
					boolean acceptRequest = Boolean.parseBoolean(answer);
					
					if (acceptRequest)
					{
						
						userBean.getUser().addContact(contactId);
						User user = UserManager.getUserConnected(contactId);
						if (user != null) {
							for(Websocket WS : user.getWebsockets()) {
								WS.emit("contactApprovedNotification", userBean.getLogin());
								WS.emit("updateContactStatus" , userBean.getLogin(), userBean.getUser().getLastLoginDateFormated());
							}
						}
						cr.setUrl("contactWindow.jsp");
					}
					else
					{
						userBean.getUser().deleteContact(contactId);
						cr.setUrl("contactRequestsWindow.jsp");
					}
				}
				
				rd = req.getRequestDispatcher("content/chat/chat.jsp");
//	    		rd.forward(req, res);
	    	}break;
	    	
	    	//Action: on est sur la chatWindow et on veut retourner a la contactWindow. Il faut donc vider le dstUserId du msgManager
			case "backToContactView":
			{    		
				cr.setAction("DefaultView");
				cr.setUrl("contactWindow.jsp");
				userBean.getMsgManager().setDstUserId(0);
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
		System.out.println("User Entering ChatServlet.doGet");
		chatRouting(req, res);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("ChatServlet: Entering doPost");
		chatRouting(req, res);
	}
	
	
}

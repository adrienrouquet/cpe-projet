package ServerCore;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.DBUserToolbox;


@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AccountServlet() throws SQLException {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("AccountServlet: Entering doGet");
		
		accountRouting(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("AccountServlet: Entering doPost");
		
		accountRouting(req,res);
	}
	
	protected void accountRouting(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		HttpSession session 	= req.getSession(true);
		RequestDispatcher rd 	= null;
		
		//On recupere le accountRouterBean de la session
		Bean.AccountRouter ar 	= (Bean.AccountRouter) session.getAttribute("accountRouterBean");
		if(ar == null)
		{
			System.out.println("Warning: accountRouterBean is null in AccountServlet");
    		ar = new Bean.AccountRouter();
			session.setAttribute("accountRouterBean", ar);
		}
		
		//On recupere le UserBean de la session
		Bean.User user 			= (Bean.User) session.getAttribute("userBean");		
		if(user == null)
		{
			System.out.println("Warning: userBean is null in AccountServlet");
			user	=  new Bean.User();
			session.setAttribute("userBean", user);
		}
		
		if(!user.getIsConnected())
		{	
			System.out.println("User disconnected/No active session");
			
			if(req.getParameter("action") != null)
			{
				
				switch(req.getParameter("action"))
				{
					case "login":
					{
						System.out.println("AccountServlet: Checking User credentials");
							
						String login 		= req.getParameter("login").trim().toLowerCase();
						String password 	= req.getParameter("password").trim();
						DBUserToolbox dbut 	= new DBUserToolbox();
						
						if (dbut.checkCredentials(login,password))
						{
							System.out.println("AccountServlet: Connecting user");
							//On recupere le user de la database et on le set dans un bean session
							//On set au passage le chatRouterBean en session aussi
							
							user = dbut.getUser(login); 
							user.setIsConnected(true);
							session.setAttribute("userBean", user);
							session.setAttribute("chatRouterBean", new Bean.ChatRouter());
							session.setAttribute("msgManagerBean", new Bean.MsgManager(user.getId()));
							System.out.println("AccountServlet: UserId " + user.getId() + " is now connected");
							
//							rd = req.getRequestDispatcher("ChatServlet");
							res.sendRedirect("ChatServlet");
						}
						else
						{
							System.out.println("AccountServlet: Wrong credentials");
							//Par defaut, on forward sur accountLogin.jsp
							ar.setUrl("accountLogin.jsp");
							rd = req.getRequestDispatcher("content/account/account.jsp");
							rd.forward(req, res);
						}	
					}break;
					default:
					{
						
					}break;				
				}
			}
			else
			{
				//Par defaut, on forward sur accountLogin.jsp
				ar.setUrl("accountLogin.jsp");
				rd = req.getRequestDispatcher("content/account/account.jsp");
				rd.forward(req, res);
			}			
		}
		else
		{
			System.out.println("User exists and is already connected");
			//Si on est deja connecte, on passe sur le ChatServlet
//			rd = req.getRequestDispatcher("ChatServlet");
			res.sendRedirect("ChatServlet");
		}

//		rd.forward(req, res);
	}
}
